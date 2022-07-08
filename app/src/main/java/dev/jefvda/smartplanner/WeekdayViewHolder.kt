package dev.jefvda.smartplanner

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.jefvda.smartplanner.databinding.WeekdayListItemBinding

class WeekdayViewHolder(itemView: View, private val clickListener: WeekdayClickListener): RecyclerView.ViewHolder(itemView) {

    private val dayTextView: TextView = itemView.findViewById(R.id.weekday_list_item_day)
    private val dateTextView: TextView = itemView.findViewById(R.id.weekday_list_item_date)

    fun populateViewHolder(weekday: Weekday) {
        itemView.setOnClickListener {
            clickListener.weekdayClicked(weekday)
        }

        dayTextView.text = weekday.day
        dateTextView.text = convertCalendarToDateString(weekday.calendar)
    }
}
