package com.example.budgetplanner

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.math.BigDecimal
import java.text.DateFormat
import java.time.LocalDate

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
        val saveOnPurchase: Purchase = Purchase("Save-On-Foods", BigDecimal("48.50"), LocalDate.parse("2023-01-01"))
        groceryPurchases.addPurchase(saveOnPurchase)
        assertEquals(1, groceryPurchases.purchases.size)
        assertEquals(BigDecimal("48.50"), groceryPurchases.getGrandTotal())
    }

    @Test
    fun addTwoPurchases() {
        val saveOnPurchase = Purchase("Save-On-Foods", BigDecimal("48.50"), LocalDate.parse("2023-01-01"))
        val urbanFarePurchase = Purchase("Urban Fare", BigDecimal("21.50"), LocalDate.parse("2023-01-01"))
        groceryPurchases.addPurchase(saveOnPurchase)
        groceryPurchases.addPurchase(urbanFarePurchase)

        assertEquals(2, groceryPurchases.purchases.size)
        assertTrue(groceryPurchases.purchases.contains(saveOnPurchase))
        assertTrue(groceryPurchases.purchases.contains(urbanFarePurchase))
        assertEquals(BigDecimal("70.00"), groceryPurchases.getGrandTotal())
    }

    @Test
    fun addTwoIdenticalPurchases() {
        val amazonPrimePurchase1 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2023-01-01"))
        val amazonPrimePurchase2 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2023-02-01"))
        assertEquals(amazonPrimePurchase2, amazonPrimePurchase1)

        groceryPurchases.addPurchase(amazonPrimePurchase1)
        groceryPurchases.addPurchase(amazonPrimePurchase2)

        assertEquals(2, groceryPurchases.purchases.size)
        assertTrue(groceryPurchases.purchases.contains(amazonPrimePurchase2))
        assertTrue(groceryPurchases.purchases.contains(amazonPrimePurchase1))
        assertEquals(BigDecimal("19.98"), groceryPurchases.getGrandTotal())
    }

    @Test
    fun deleteOnePurchase() {
        val saveOnPurchase: Purchase = Purchase("Save-On-Foods", BigDecimal("48.50"), LocalDate.parse("2023-01-01"))
        groceryPurchases.addPurchase(saveOnPurchase)
        assertEquals(1, groceryPurchases.purchases.size)
        assertEquals(BigDecimal("48.50"), groceryPurchases.getGrandTotal())

        groceryPurchases.deletePurchase(saveOnPurchase)
        assertEquals(0, groceryPurchases.purchases.size)
        assertEquals(BigDecimal("0"), groceryPurchases.getGrandTotal())
    }

    @Test
    fun deleteOneIdenticalPurchase() {
        val amazonPrimePurchase1 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2023-01-01"))
        val amazonPrimePurchase2 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2023-02-01"))
        assertEquals(amazonPrimePurchase2, amazonPrimePurchase1)

        groceryPurchases.addPurchase(amazonPrimePurchase1)
        groceryPurchases.addPurchase(amazonPrimePurchase2)

        // standard testing before removal, for sanity
        assertEquals(2, groceryPurchases.purchases.size)
        assertTrue(groceryPurchases.purchases.contains(amazonPrimePurchase2))
        assertTrue(groceryPurchases.purchases.contains(amazonPrimePurchase1))
        assertEquals(BigDecimal("19.98"), groceryPurchases.getGrandTotal())

        groceryPurchases.deletePurchase(amazonPrimePurchase2)
        assertEquals(1, groceryPurchases.purchases.size)
        assertEquals(BigDecimal("9.99"), groceryPurchases.getGrandTotal())
        // check both because they should be equivalent, but only one should be removed.
        assertTrue(groceryPurchases.purchases.contains(amazonPrimePurchase2))
        assertTrue(groceryPurchases.purchases.contains(amazonPrimePurchase1))
    }

}