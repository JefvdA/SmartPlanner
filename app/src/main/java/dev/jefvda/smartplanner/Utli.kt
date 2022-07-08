package dev.jefvda.smartplanner

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun convertCalendarToDateString(calendar: Calendar): String {
    return SimpleDateFormat("dd-MM-yyyy")
        .format(calendar.time).toString()
}

fun convertCalendarToTimeString(calendar: Calendar): String {
    return "${calendar.get(Calendar.HOUR_OF_DAY)}:${"%02d".format(calendar.get(Calendar.MINUTE))}"
}

fun getDateOfMondayInTwoWeeks(currentCalendar: Calendar): Calendar {
    val newCalendar = Calendar.getInstance().let {
        it.set(Calendar.DAY_OF_YEAR, currentCalendar.get(Calendar.DAY_OF_YEAR))
        it
    }

    while (newCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY || newCalendar.get(Calendar.WEEK_OF_YEAR) < currentCalendar.get(Calendar.WEEK_OF_YEAR) + 2) {
        newCalendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    return newCalendar
}