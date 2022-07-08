package dev.jefvda.smartplanner.dayoverview

import androidx.lifecycle.*
import dev.jefvda.smartplanner.database.ActivityEntity
import dev.jefvda.smartplanner.database.ActivityDao
import kotlinx.coroutines.launch

class ActivityListViewModel(private var datasource: ActivityDao): ViewModel() {

    private val _activityList = MutableLiveData<MutableList<ActivityEntity>>()
    val activityList: LiveData<MutableList<ActivityEntity>>
        get() = _activityList

    fun saveActivity(activity: ActivityEntity) {
        viewModelScope.launch {
            datasource.insert(activity)
            readActivities(activity.weekday)
        }
    }

    fun readActivities(weekday: String) {
        viewModelScope.launch {
            _activityList.postValue(datasource.selectAllForWeekday(weekday).toMutableList())
        }
    }
}
