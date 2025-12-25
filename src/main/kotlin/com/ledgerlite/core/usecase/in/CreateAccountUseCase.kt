package com.ledgerlite.core.usecase.`in`

import com.ledgerlite.core.dto.command.CreateAccountCommand
import com.ledgerlite.core.dto.result.AccountResult

interface CreateAccountUseCase {
    fun execute(command: CreateAccountCommand): AccountResult
}