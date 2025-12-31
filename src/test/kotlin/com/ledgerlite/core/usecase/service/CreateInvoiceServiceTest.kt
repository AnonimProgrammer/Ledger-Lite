package com.ledgerlite.core.usecase.service

import com.ledgerlite.core.domain.Invoice
import com.ledgerlite.core.domain.vo.InvoiceItem
import com.ledgerlite.core.domain.vo.InvoiceStatus
import com.ledgerlite.core.domain.vo.Money
import com.ledgerlite.core.usecase.dto.command.CreateInvoiceCommand
import com.ledgerlite.core.usecase.dto.command.InvoiceItemObject
import com.ledgerlite.core.usecase.out.InvoiceRepository
import org.junit.jupiter.api.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import java.math.BigDecimal
import java.time.LocalDate
import java.util.UUID

class CreateInvoiceServiceTest {
    private val invoiceRepository: InvoiceRepository = mock()

    private val createInvoiceService = CreateInvoiceService(invoiceRepository)

    private val customerId: UUID = UUID.randomUUID()

    @Test
    fun createsInvoice() {
        val command = getCommand()
        val savedInvoice = getSavedInvoice()
        whenever(invoiceRepository.save(any())).thenReturn(savedInvoice)

        createInvoiceService.execute(command)

        verify(invoiceRepository).save(any())
    }

    private fun getCommand(): CreateInvoiceCommand =
        CreateInvoiceCommand(
            customerId = customerId,
            dueDate = LocalDate.now().plusDays(10),
            items = listOf(
                InvoiceItemObject(
                    description = "Item 1",
                    quantity = 2,
                    unitPrice = BigDecimal.valueOf(50))
            )
        )

    private fun getSavedInvoice() =
        Invoice(
            id = customerId,
            customerId = UUID.randomUUID(),
            status = InvoiceStatus.SENT,
            dueDate = LocalDate.now().plusDays(10),
            items = listOf(
                InvoiceItem(
                    description = "Item 1",
                    quantity = 2,
                    unitPrice = Money.of(BigDecimal.valueOf(50)))
            )
        )
}