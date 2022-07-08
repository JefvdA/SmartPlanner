package dev.jefvda.smartplanner

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
class Activity(
    var description: String,
    var startTime: Calendar
): Parcelable {}