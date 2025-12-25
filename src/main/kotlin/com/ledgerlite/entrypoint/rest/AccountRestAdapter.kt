package com.ledgerlite.entrypoint.rest

import com.ledgerlite.core.dto.command.CreateAccountCommand
import com.ledgerlite.core.dto.result.AccountResult
import com.ledgerlite.core.usecase.`in`.CreateAccountUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/accounts")
class AccountRestAdapter(
    private val createAccountUseCase: CreateAccountUseCase,
) {

    @PostMapping
    fun createAccount(@RequestBody command: CreateAccountCommand): ResponseEntity<AccountResult> {
        val result = createAccountUseCase.execute(command)
        return ResponseEntity.status(HttpStatus.CREATED).body(result)
    }
}