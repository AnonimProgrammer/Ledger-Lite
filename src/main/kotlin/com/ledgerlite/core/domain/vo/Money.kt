package com.ledgerlite.core.domain.vo

import java.math.BigDecimal

@ConsistentCopyVisibility
data class Money private constructor(
    val amount: BigDecimal
) {
    fun isPositive(): Boolean = amount > BigDecimal.ZERO
    fun isZero(): Boolean = amount.compareTo(BigDecimal.ZERO) == 0
    fun absolute(): Money = of(amount.abs())

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