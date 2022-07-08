package dev.jefvda.smartplanner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WeekdayListAdapter(var weekdayList: MutableList<Weekday>): RecyclerView.Adapter<WeekdayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekdayViewHolder {
        return WeekdayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weekday_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: WeekdayViewHolder, position: Int) {
        holder.populateViewHolder(weekdayList[position])
    }

    override fun getItemCount(): Int {
        return weekdayList.size
    }
}