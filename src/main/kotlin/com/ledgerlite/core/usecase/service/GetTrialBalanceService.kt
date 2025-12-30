package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.Account
import com.ledgerlite.core.domain.vo.JournalLine
import com.ledgerlite.core.domain.vo.Money
import com.ledgerlite.core.usecase.dto.result.TrialBalanceResult
import com.ledgerlite.core.usecase.dto.result.vo.BalanceSide
import com.ledgerlite.core.usecase.dto.result.vo.TrialBalanceLine
import com.ledgerlite.core.usecase.`in`.GetTrialBalanceUseCase
import com.ledgerlite.core.usecase.out.AccountRepository
import com.ledgerlite.core.usecase.out.JournalEntryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

class GetTrialBalanceService(
    private val accountRepository: AccountRepository,
    private val journalEntryRepository: JournalEntryRepository,
    private val logger: Logger = LoggerFactory.getLogger(GetTrialBalanceService::class.java),
) : GetTrialBalanceUseCase {

    override fun execute(from: LocalDate, to: LocalDate): TrialBalanceResult {
        logger.info("Getting trial balance. Period: from $from to $to")

        val accounts = accountRepository.findAll()
        val lines = journalEntryRepository.findJournalLinesBetween(from, to)

        return buildResult(from, to, accounts, lines)
    }

    private fun buildResult(
        from: LocalDate,
        to: LocalDate,
        accounts: List<Account>,
        lines: List<JournalLine>
        ): TrialBalanceResult {

        val groupedAccounts = lines.groupBy { it.accountId }
        val trialLines = accounts.map { account -> buildLines(account, groupedAccounts) }

        val totalDebit = calculateTotalDebit(trialLines)
        val totalCredit = calculateTotalCredit(trialLines)

        logger.info("Trial balance generated. Total Debit: $totalDebit, Total Credit: $totalCredit")
        return TrialBalanceResult(
            from = from,
            to = to,
            lines = trialLines,
            totalDebit = totalDebit,
            totalCredit = totalCredit
        )
    }

    private fun calculateTotalDebit(trialLines: List<TrialBalanceLine>): BigDecimal =
        trialLines
            .filter { it.balanceSide == BalanceSide.DEBIT }
            .fold(BigDecimal.ZERO) { acc, line -> acc.add(line.balance) }

    private fun calculateTotalCredit(trialLines: List<TrialBalanceLine>): BigDecimal =
        trialLines
            .filter { it.balanceSide == BalanceSide.CREDIT }
            .fold(BigDecimal.ZERO) { acc, line -> acc.add(line.balance) }

    private fun buildLines(
        account: Account,
        groupedAccounts: Map<UUID, List<JournalLine>>
    ): TrialBalanceLine {
        val accountLines = groupedAccounts[account.id].orEmpty()

        val debitTotal = accountLines
            .fold(Money.zero()) { acc, line -> acc + line.debit }

        val creditTotal = accountLines
            .fold(Money.zero()) { acc, line -> acc + line.credit }

        val balance = debitTotal - creditTotal
        return TrialBalanceLine(
            accountId = account.id,
            accountCode = account.code,
            accountName = account.name,
            accountType = account.type,
            isSystemAccount = account.isSystemAccount,
            currency = account.currency,
            debitTotal = debitTotal.amount,
            creditTotal = creditTotal.amount,
            balance = balance.absolute().amount,
            balanceSide = getBalanceSide(balance)
        )
    }

    private fun getBalanceSide(balance: Money): BalanceSide =
        when {
            balance.isZero() -> BalanceSide.ZERO
            balance.isPositive() -> BalanceSide.DEBIT
            else -> BalanceSide.CREDIT
        }
}
