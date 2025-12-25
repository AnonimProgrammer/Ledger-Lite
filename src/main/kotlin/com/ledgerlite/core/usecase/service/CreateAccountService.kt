package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.Account
import com.ledgerlite.core.dto.command.CreateAccountCommand
import com.ledgerlite.core.dto.result.AccountResult
import com.ledgerlite.core.usecase.`in`.CreateAccountUseCase
import com.ledgerlite.core.usecase.out.AccountRepository

class CreateAccountService(
    private val accountRepository: AccountRepository
) : CreateAccountUseCase {

    override fun execute(command: CreateAccountCommand): AccountResult {
        val account = Account.create(
            name = command.name,
            type = command.type
        )

        val saved = accountRepository.save(account)
        return AccountResult.from(saved)
    }
}
