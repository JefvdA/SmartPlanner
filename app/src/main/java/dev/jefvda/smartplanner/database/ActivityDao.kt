package dev.jefvda.smartplanner.database

import androidx.room.*

@Dao
interface ActivityDao {
    @Insert
    suspend fun insert(activity: ActivityEntity)

    @Update
    suspend fun update(activity: ActivityEntity)

    @Delete
    suspend fun delete(activity: ActivityEntity)

    @Query("SELECT * FROM tblActivities WHERE weekday=:weekday ORDER BY startTime DESC")
    suspend fun selectAllForWeekday(weekday: String): MutableList<ActivityEntity>
}