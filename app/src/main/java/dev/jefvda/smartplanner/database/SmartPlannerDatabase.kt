package dev.jefvda.smartplanner.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ActivityEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class SmartPlannerDatabase: RoomDatabase() {

    abstract val activityDao: ActivityDao

    companion object {
        @Volatile
        private var instance: SmartPlannerDatabase? = null

        @Synchronized
        fun getInstance(context: Context): SmartPlannerDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(context.applicationContext, SmartPlannerDatabase::class.java,
                    "smart_planner_database")
                    .fallbackToDestructiveMigration()
                    .build()

            return instance as SmartPlannerDatabase
        }
    }
}