package com.ledgerlite.entrypoint.rest

import com.ledgerlite.core.dto.command.CreateInvoiceCommand
import com.ledgerlite.core.dto.command.MarkInvoicePaidCommand
import com.ledgerlite.core.dto.command.SendInvoiceCommand
import com.ledgerlite.core.dto.result.InvoiceResult
import com.ledgerlite.core.usecase.`in`.CreateInvoiceUseCase
import com.ledgerlite.core.usecase.`in`.MarkInvoicePaidUseCase
import com.ledgerlite.core.usecase.`in`.SendInvoiceUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/invoices")
class InvoiceRestAdapter(
    private val createInvoiceUseCase: CreateInvoiceUseCase,
    private val sendInvoiceUseCase: SendInvoiceUseCase,
    private val markInvoicePaidUseCase: MarkInvoicePaidUseCase,
) {

    @PostMapping
    fun createInvoice(@RequestBody command: CreateInvoiceCommand): ResponseEntity<InvoiceResult> {
        val result = createInvoiceUseCase.execute(command)
        return ResponseEntity.status(HttpStatus.CREATED).body(result)
    }

    @PatchMapping("/send")
    fun sendInvoice(@RequestBody command: SendInvoiceCommand): ResponseEntity<Void> {
        sendInvoiceUseCase.execute(command)
        return ResponseEntity.ok().build()
    }

    @PatchMapping("/mark-paid")
    fun markInvoicePaid(@RequestBody command: MarkInvoicePaidCommand): ResponseEntity<Void> {
        markInvoicePaidUseCase.execute(command)
        return ResponseEntity.ok().build()
    }
}