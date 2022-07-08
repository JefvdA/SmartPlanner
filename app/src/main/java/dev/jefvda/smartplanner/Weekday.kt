package dev.jefvda.smartplanner

import java.util.*

class Weekday(
    var day: String,
    var dayOfYear: Int,
    var calendar: Calendar = Calendar.getInstance().let {
        it.set(Calendar.DAY_OF_YEAR, dayOfYear)
        it
    }
) {}