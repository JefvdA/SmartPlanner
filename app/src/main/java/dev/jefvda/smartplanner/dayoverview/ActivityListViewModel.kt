package dev.jefvda.smartplanner.dayoverview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.jefvda.smartplanner.database.Activity
import dev.jefvda.smartplanner.database.SmartPlannerDatabase
import dev.jefvda.smartplanner.database.Weekday
import kotlinx.coroutines.launch

class ActivityListViewModel(application: Application): AndroidViewModel(application) {

    private val context = application.applicationContext
    private val _activityList = MutableLiveData<MutableList<Activity>>()
    val activityList: LiveData<MutableList<Activity>>
        get() = _activityList

    private val database = SmartPlannerDatabase.getInstance(context)

    fun saveActivity(activity: Activity) {
        viewModelScope.launch {
            database.activityDao.insert(activity)
        }
    }

    fun readActivities(weekday: Weekday) {
        viewModelScope.launch {
            _activityList.postValue(database.activityDao.selectAllForWeekday(weekday.day))
        }
    }
}