package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.usecase.dto.command.CreateAccountCommand
import com.ledgerlite.core.usecase.dto.result.AccountResult

interface CreateAccountUseCase {
    fun execute(command: CreateAccountCommand): AccountResult
}