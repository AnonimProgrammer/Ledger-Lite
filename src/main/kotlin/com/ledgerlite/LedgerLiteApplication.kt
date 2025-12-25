package com.ledgerlite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LedgerLiteApplication

fun main(args: Array<String>) {
    runApplication<LedgerLiteApplication>(*args)
}
