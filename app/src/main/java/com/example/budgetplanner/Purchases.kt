package com.example.budgetplanner

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.budgetplanner.util.PeriodUtil
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Period
import kotlin.time.DurationUnit

class Purchases (
    val title: String = "",
    val purchases: ArrayList<Purchase> = ArrayList<Purchase>()
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun addPurchase(purchase: Purchase) {
        purchases.add(purchase)

        if (purchases.size <= 1) return

        if (purchases[purchases.size - 1].date < purchases[purchases.size - 2].date) {
            purchases.sortBy { it.date }
        }
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
        val monthlyTotals = HashMap<String, BigDecimal>()
        for (purchase in purchases) {
            val key = purchase.date.year.toString().plus(purchase.date.month.toString())
            monthlyTotals[key] = if (monthlyTotals.containsKey(key)) {
                purchase.price.add(monthlyTotals[key])
            } else {
                purchase.price
            }
        }
        return monthlyTotals
    }

    fun getWeeklyTotals(): HashMap<String, BigDecimal> {
        val weeklyTotals = HashMap<String, BigDecimal>()
        var currentObservedWeek = LocalDate.now()
        var i = purchases.size - 1
        var key = currentObservedWeek.toString()

        // iterate backwards because the most recent transaction will be last
        // needs further testing to make sure purchases aren't being skipped over. whatever.
        while (i >= 0) {
            while (PeriodUtil.periodToDays(purchases[i].date.until(currentObservedWeek)) > 7) {
                currentObservedWeek = currentObservedWeek.minusWeeks(1)
                key = currentObservedWeek.toString()
            }
            weeklyTotals[key] = if (weeklyTotals.containsKey(key)) {
                purchases[i].price.add(weeklyTotals[key])
            } else {
                purchases[i].price
            }
            i--
        }
        return weeklyTotals
    }

    fun getDailyTotals(): HashMap<String, BigDecimal> {
        val dailyTotals = HashMap<String, BigDecimal>()
        for (purchase in purchases) {
            val key = purchase.date.toString()
            dailyTotals[key] = if (dailyTotals.containsKey(key)) {
                purchase.price.add(dailyTotals[key])
            } else {
                purchase.price
            }
        }
        return dailyTotals
    }
}