package dev.jefvda.smartplanner.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun calendarFromTimestamp(value: Long): Calendar {
        return Calendar.getInstance().apply {
            timeInMillis = value
        }
    }

    @TypeConverter
    fun timestampFromCalendar(calendar: Calendar): Long {
        return calendar.timeInMillis
    }
}