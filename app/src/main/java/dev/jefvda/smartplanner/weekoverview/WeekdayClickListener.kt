package dev.jefvda.smartplanner.weekoverview

import dev.jefvda.smartplanner.database.Weekday

fun interface WeekdayClickListener {
    fun weekdayClicked(weekday: Weekday)
}