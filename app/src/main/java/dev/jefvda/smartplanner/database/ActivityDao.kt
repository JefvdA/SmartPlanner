package dev.jefvda.smartplanner.database

import androidx.room.*

@Dao
interface ActivityDao {
    @Insert
    @JvmSuppressWildcards
    suspend fun insert(activity: ActivityEntity)

    @Update
    @JvmSuppressWildcards
    suspend fun update(activity: ActivityEntity)

    @Delete
    @JvmSuppressWildcards
    suspend fun delete(activity: ActivityEntity)

    @Query("SELECT * FROM tblActivities WHERE weekday=:weekday ORDER BY startTime DESC")
    @JvmSuppressWildcards
    fun selectAllForWeekday(weekday: String): List<ActivityEntity>
}