package com.example.budgetplanner

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.math.BigDecimal

class PurchasesTests {
    lateinit var groceryPurchases: Purchases

    @Before
    fun beforeEach() {
        groceryPurchases = Purchases("Groceries")
    }

    @Test
    fun sanity() {
        assertEquals(groceryPurchases.title, "Groceries")
        assertEquals(groceryPurchases.purchases.size, 0)
    }


    @Test
    fun addOnePurchase() {
        val saveOnPurchase: Purchase = Purchase("Save-On-Foods", BigDecimal("48.50"))
        groceryPurchases.addPurchase(saveOnPurchase)
        assertEquals(1, groceryPurchases.purchases.size)
        assertEquals(BigDecimal("48.50"), groceryPurchases.getGrandTotal())
    }

}