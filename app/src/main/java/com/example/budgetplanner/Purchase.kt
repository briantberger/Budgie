package com.example.budgetplanner

import java.math.BigDecimal
import java.text.DecimalFormat

class Purchase (
    val vendor: String,
    val price: BigDecimal
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Purchase

        if (vendor != other.vendor) return false
        if (price != other.price) return false

        return true
    }

    override fun hashCode(): Int {
        var result = vendor.hashCode()
        result = 31 * result + price.hashCode()
        return result
    }
}