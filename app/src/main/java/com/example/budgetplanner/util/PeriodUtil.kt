package com.example.budgetplanner.util

import java.time.Period

object PeriodUtil {
    fun periodToDays(p: Period): Int {
        return (p.years * 12 + p.months) * 30 + p.days
    }
}