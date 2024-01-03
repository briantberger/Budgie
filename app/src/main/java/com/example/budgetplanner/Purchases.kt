package com.example.budgetplanner

import android.os.Build
import androidx.annotation.RequiresApi
import java.math.BigDecimal

class Purchases (
    val title: String,
    val purchases: ArrayList<Purchase> = ArrayList<Purchase>()
) {

    fun addPurchase(purchase: Purchase) {
        purchases.add(purchase)
    }

    fun deletePurchase(purchase: Purchase) {
        purchases.remove(purchase)
    }

    fun getGrandTotal(): BigDecimal {
        var total = BigDecimal("0")
        for (purchase in purchases) {
            total = total.add(purchase.price)
        }
        return total
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getMonthlyTotals(): HashMap<String, BigDecimal> {
        val monthlyBreakdown = HashMap<String, BigDecimal>()
        for (purchase in purchases) {
            val key = purchase.date.year.toString().plus(purchase.date.month.toString())

            monthlyBreakdown[key] = if (monthlyBreakdown.containsKey(key)) {
                purchase.price.add(monthlyBreakdown[key])
            } else {
                purchase.price
            }
        }
        return monthlyBreakdown
    }
}