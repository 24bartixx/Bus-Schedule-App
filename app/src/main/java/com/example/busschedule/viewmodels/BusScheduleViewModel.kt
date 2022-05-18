package com.example.busschedule.viewmodels

import androidx.lifecycle.ViewModel
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao
import kotlinx.coroutines.flow.Flow

class BusScheduleViewModel(private val scheduleDao: ScheduleDao): ViewModel() {

    // these are suspend functions

    // get schedule for the first screen
    fun fullSchedule(): Flow<List<Schedule>> = scheduleDao.getAll()

    // get schedule for the second screen
    fun scheduleForStopName(stopName: String): Flow<List<Schedule>> = scheduleDao.getByStopName(stopName)

}