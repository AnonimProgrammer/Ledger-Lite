package com.ledgerlite.dataprovider.jpa.mapper

import com.ledgerlite.core.domain.Invoice
import com.ledgerlite.core.domain.vo.InvoiceItem
import com.ledgerlite.core.domain.vo.Money
import com.ledgerlite.dataprovider.jpa.entity.InvoiceJpaEntity
import com.ledgerlite.dataprovider.jpa.entity.embeddable.InvoiceItemEmbeddable

object InvoiceJpaMapper {

    fun toJpa(domain: Invoice): InvoiceJpaEntity =
        InvoiceJpaEntity(
            id = domain.id,
            customerId = domain.customerId,
            status = domain.status,
            dueDate = domain.dueDate,
            items = domain.items.map {
                InvoiceItemEmbeddable(
                    description = it.description,
                    quantity = it.quantity,
                    unitPrice = it.unitPrice.amount
                )
            }
        )

    fun toDomain(entity: InvoiceJpaEntity): Invoice =
        Invoice(
            id = entity.id,
            customerId = entity.customerId,
            status = entity.status,
            dueDate = entity.dueDate,
            items = entity.items.map {
                InvoiceItem(
                    description = it.description,
                    quantity = it.quantity,
                    unitPrice = Money.of(it.unitPrice)
                )
            }
        )
}
