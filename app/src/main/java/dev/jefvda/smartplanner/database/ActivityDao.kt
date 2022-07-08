package dev.jefvda.smartplanner.database

import dev.jefvda.smartplanner.database.Activity
import androidx.room.*

@Dao
interface ActivityDao {
    @Insert
    suspend fun insert(activity: Activity)

    @Update
    suspend fun update(activity: Activity)

    @Delete
    suspend fun delete(activity: Activity)

    @Query("SELECT * FROM tblActivities WHERE weekday=:weekday ORDER BY startTime DESC")
    suspend fun selectAllForWeekday(weekday: String)
}