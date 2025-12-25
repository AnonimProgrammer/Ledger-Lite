package com.ledgerlite.dataprovider.exception

class MissingAccountException(
    name: String,
): RuntimeException(
    "Missing account. Name: $name"
) {}