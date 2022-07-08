package dev.jefvda.smartplanner.dayoverview

import android.app.Application
import androidx.lifecycle.*
import dev.jefvda.smartplanner.database.ActivityEntity
import dev.jefvda.smartplanner.database.ActivityDao
import dev.jefvda.smartplanner.database.Weekday
import kotlinx.coroutines.launch

class ActivityListViewModel(private var datasource: ActivityDao): ViewModel() {

    private val _activityList = MutableLiveData<MutableList<ActivityEntity>>()
    val activityList: LiveData<MutableList<ActivityEntity>>
        get() = _activityList

    fun saveActivity(activity: ActivityEntity) {
        viewModelScope.launch {
            datasource.insert(activity)
        }
    }

    fun readActivities(weekday: Weekday) {
        viewModelScope.launch {
            _activityList.postValue(datasource.selectAllForWeekday(weekday.day).toMutableList())
        }
    }
}
