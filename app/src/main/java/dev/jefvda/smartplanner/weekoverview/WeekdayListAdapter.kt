package dev.jefvda.smartplanner.weekoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jefvda.smartplanner.R
import dev.jefvda.smartplanner.database.Weekday

class WeekdayListAdapter(var weekdayList: MutableList<Weekday>, private val clickListener: WeekdayClickListener): RecyclerView.Adapter<WeekdayViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekdayViewHolder {
        return WeekdayViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weekday_list_item, parent, false), clickListener)
    }

    override fun onBindViewHolder(holder: WeekdayViewHolder, position: Int) {
        holder.populateViewHolder(weekdayList[position])
    }

    override fun getItemCount(): Int {
        return weekdayList.size
    }
}