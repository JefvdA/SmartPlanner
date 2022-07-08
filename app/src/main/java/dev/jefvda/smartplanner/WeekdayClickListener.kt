package dev.jefvda.smartplanner

import dev.jefvda.smartplanner.database.Weekday

fun interface WeekdayClickListener {
    fun weekdayClicked(weekday: Weekday)
}