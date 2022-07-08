package dev.jefvda.smartplanner.database

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Activity(
    var description: String,
    var startTime: Calendar
): Parcelable {}