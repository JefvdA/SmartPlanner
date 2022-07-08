package dev.jefvda.smartplanner.dayoverview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.jefvda.smartplanner.database.ActivityDao

class ActivityListViewModelFactory(
    private val dataSource: ActivityDao) : ViewModelProvider.Factory {
        @Suppress("unchecked_cast")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ActivityListViewModel::class.java)) {
                return ActivityListViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}