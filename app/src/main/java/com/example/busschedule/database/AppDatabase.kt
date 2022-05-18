package com.example.busschedule.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao

/**
 * class to create databases
 * can control models, DAO classes, and any database setup
 *
 * Responsibilities of AppDatabase class:
 * - specify which entities are defined in the database
 * - provide access to a single instance of each DAO class
 * - Perform any additional setup
 */

// all entity types are listed in an array
// the version number is incremented each time a schema change is made
@Database(entities = arrayOf(Schedule::class), version = 1)
abstract class AppDatabase: RoomDatabase() {


    // allow other classes to easy access to the DAO classes
    abstract fun scheduleDao(): ScheduleDao

    // ensure that only one instance of the database exists
    // the instance is stored in the companion object
    companion object {
        // instance of a database
        @Volatile
        private var INSTANCE: AppDatabase? = null

        // a function to return the AppDatabase instance
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                    .createFromAsset("database/bus_schedule.db")
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}