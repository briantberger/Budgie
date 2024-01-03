package com.example.budgetplanner

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun purchases_areEqual() {
        val purchase1 = Purchase("Amazon", 15.00)
        val purchase2 = Purchase("Amazon", 15.00)
        assertEquals(purchase1, purchase2);
    }
}