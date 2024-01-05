package com.example.budgetplanner

import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigDecimal
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
class Purchase constructor(
    val vendor: String,
    val price: BigDecimal,
    val date: LocalDate = LocalDate.now()
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