package com.example.busschedule.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.busschedule.database.schedule.ScheduleDao

/**
 * a factory class
 * instantiate ViewModel, as fragments cannot handle all the memory management
 */
class BusScheduleViewModelFactory(private val scheduleDao: ScheduleDao): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(BusScheduleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BusScheduleViewModel(scheduleDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}