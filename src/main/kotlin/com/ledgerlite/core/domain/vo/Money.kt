package com.ledgerlite.core.domain.vo

import java.math.BigDecimal

data class Money private constructor(
    val amount: BigDecimal
) {

    fun isPositive(): Boolean = amount > BigDecimal.ZERO

    operator fun plus(other: Money) = Money(this.amount + other.amount)
    operator fun minus(other: Money) = Money(this.amount - other.amount)
    operator fun times(other: Int) = Money(this.amount * other.toBigDecimal())

    companion object {
        fun of(value: BigDecimal): Money {
            require(value >= BigDecimal.ZERO) { "Money cannot be negative." }
            return Money(value)
        }

        fun zero() = Money(BigDecimal.ZERO)
    }
}