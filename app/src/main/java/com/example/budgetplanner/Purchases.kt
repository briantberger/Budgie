package com.example.budgetplanner

import java.math.BigDecimal

class Purchases (
    val title: String,
    val purchases: ArrayList<Purchase> = ArrayList<Purchase>()
) {

    fun addPurchase(purchase: Purchase) {
        purchases.add(purchase);
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
}