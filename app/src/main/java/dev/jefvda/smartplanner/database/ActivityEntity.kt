package dev.jefvda.smartplanner.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "tblActivities")
class ActivityEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var description: String,
    var startTime: Calendar,
    var weekday: String
): Parcelable {}