package com.ledgerlite.dataprovider.exception

class MissingAccountException(
    code: String,
    ): RuntimeException("Missing account. Name: $code")