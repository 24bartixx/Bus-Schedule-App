package com.example.busschedule

import android.app.Application
import com.example.busschedule.database.AppDatabase

class BusScheduleApplication : Application() {

    // lazy property holds holds the result of getDatabase() - which means it holds a database basically
    val database: AppDatabase by lazy { AppDatabase.getDatabase(this) }

    // we also need to make a change to the manifest to make sure that BusScheduleApplication class is used

}