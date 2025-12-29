package com.ledgerlite.dataprovider.exception

import java.util.UUID

class InvoiceNotFoundException(
    invoiceId: UUID,
    ): RuntimeException("Invoice with id: $invoiceId not found.")