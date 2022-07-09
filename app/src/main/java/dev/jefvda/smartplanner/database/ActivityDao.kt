package dev.jefvda.smartplanner.database

import androidx.room.*

@Dao
interface ActivityDao {
    @Insert
    fun insert(activity: ActivityEntity)

    @Update
    fun update(activity: ActivityEntity)

    @Delete
    fun delete(activity: ActivityEntity)

    @Query("SELECT * FROM tblActivities WHERE weekday=:weekday ORDER BY startTime DESC")
    fun selectAllForWeekday(weekday: String): List<ActivityEntity>

    @Query("DELETE FROM tblActivities")
    fun clear()
}