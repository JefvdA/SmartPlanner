package dev.jefvda.smartplanner

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Weekday(
    var day: String,
    var dayOfYear: Int,
    var calendar: Calendar = Calendar.getInstance().let {
        it.set(Calendar.DAY_OF_YEAR, dayOfYear)
        it
    }
): Parcelable {}