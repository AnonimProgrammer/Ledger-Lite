package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.Account
import com.ledgerlite.core.usecase.dto.command.CreateAccountCommand
import com.ledgerlite.core.usecase.dto.result.AccountResult
import com.ledgerlite.core.usecase.`in`.CreateAccountUseCase
import com.ledgerlite.core.usecase.mapper.AccountMapper
import com.ledgerlite.core.usecase.out.AccountRepository
import java.util.logging.Logger

class CreateAccountService(
    private val accountRepository: AccountRepository,
    private val logger: Logger = Logger.getLogger(CreateAccountService::class.java.name),
) : CreateAccountUseCase {

    override fun execute(command: CreateAccountCommand): AccountResult {
        logger.info("Creating account. Name: ${command.name}, Type: ${command.type}")

        val account = Account.create(
            name = command.name,
            type = command.type
        )
        val savedAccount = accountRepository.save(account)
        logger.info("Account created. ID: ${savedAccount.id}")

        return AccountMapper.from(savedAccount)
    }
}
