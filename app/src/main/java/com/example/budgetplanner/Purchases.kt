package com.example.budgetplanner

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
}