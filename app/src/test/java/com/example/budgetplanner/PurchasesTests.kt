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
    fun addPurchasesAndSeeTheyAreSorted() {
        val purchase1 = Purchase("store1", BigDecimal("5.00"), LocalDate.parse("2023-01-01"))
        val purchase2 = Purchase("store2", BigDecimal("10.00"), LocalDate.parse("2023-01-02"))
        val purchase3 = Purchase("store3", BigDecimal("15.00"), LocalDate.parse("2023-01-03"))
        val purchase4 = Purchase("store4", BigDecimal("20.00"), LocalDate.parse("2023-01-04"))
        val purchase5 = Purchase("store5", BigDecimal("25.00"), LocalDate.parse("2023-01-05"))

        val expected = arrayListOf(purchase1, purchase2, purchase3, purchase4, purchase5)

        groceryPurchases.addPurchase(purchase1)
        groceryPurchases.addPurchase(purchase2)
        groceryPurchases.addPurchase(purchase5)
        groceryPurchases.addPurchase(purchase4)
        groceryPurchases.addPurchase(purchase3)

        assertEquals(expected, groceryPurchases.purchases)
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

    @Test
    fun getBasicMonthlyBreakdown() {
        val amazonPrimePurchase1 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2023-01-01"))
        val amazonPrimePurchase2 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2023-02-01"))

        groceryPurchases.addPurchase(amazonPrimePurchase1)
        groceryPurchases.addPurchase(amazonPrimePurchase2)

        val actual = groceryPurchases.getMonthlyTotals()

        assertTrue(actual.containsKey("2023JANUARY"))
        assertTrue(actual.containsKey("2023FEBRUARY"))
        assertEquals(actual["2023JANUARY"], BigDecimal("9.99"))
        assertEquals(actual["2023FEBRUARY"], BigDecimal("9.99"))
    }

    @Test
    fun getComplexMonthlyBreakdown() {
        val amazonPrimePurchase1 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2023-01-01"))
        val amazonPrimePurchase2 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2023-02-01"))
        val saveOnPurchase = Purchase("Save-On-Foods", BigDecimal("48.50"), LocalDate.parse("2023-01-01"))
        val urbanFarePurchase = Purchase("Urban Fare", BigDecimal("21.50"), LocalDate.parse("2023-02-01"))

        groceryPurchases.addPurchase(amazonPrimePurchase1)
        groceryPurchases.addPurchase(amazonPrimePurchase2)
        groceryPurchases.addPurchase(saveOnPurchase)
        groceryPurchases.addPurchase(urbanFarePurchase)

        val actual = groceryPurchases.getMonthlyTotals()

        assertTrue(actual.containsKey("2023JANUARY"))
        assertTrue(actual.containsKey("2023FEBRUARY"))

        assertEquals(actual["2023JANUARY"], BigDecimal("58.49"))
        assertEquals(actual["2023FEBRUARY"], BigDecimal("31.49"))
    }

    @Test
    fun getWeeklyBreakdown() {
        val amazonPrimePurchase1 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2024-01-03"))
        val amazonPrimePurchase2 = Purchase("Amazon", BigDecimal("9.99"), LocalDate.parse("2024-01-02"))
        val saveOnPurchase = Purchase("Save-On-Foods", BigDecimal("48.50"), LocalDate.parse("2023-12-25"))
        val urbanFarePurchase = Purchase("Urban Fare", BigDecimal("21.50"), LocalDate.parse("2023-12-24"))

        groceryPurchases.addPurchase(amazonPrimePurchase1)
        groceryPurchases.addPurchase(amazonPrimePurchase2)
        groceryPurchases.addPurchase(saveOnPurchase)
        groceryPurchases.addPurchase(urbanFarePurchase)

        val actual = groceryPurchases.getWeeklyTotals()
        val expected = hashMapOf(Pair<String, BigDecimal>("2024-01-03", BigDecimal("19.98")), Pair<String, BigDecimal>("2023-12-27", BigDecimal("70.00")))

        for (pair in expected) {
            assertTrue(actual.containsKey(pair.key) && actual[pair.key] == pair.value)
        }
    }
}