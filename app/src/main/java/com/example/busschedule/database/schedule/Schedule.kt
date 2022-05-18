package com.example.busschedule.database.schedule

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * model class / entity
 * describes the structure of a database
 */
// (CODE) @Entity(tableName="schedule")
@Entity
data class Schedule (
    @PrimaryKey val id: Int,
    @NonNull @ColumnInfo(name = "stop_name") val stopName: String,
    // this property is a Unix timestamp that can be converted into usable date
    @NonNull @ColumnInfo(name = "arrival_time") val arrivalTime: Int
)