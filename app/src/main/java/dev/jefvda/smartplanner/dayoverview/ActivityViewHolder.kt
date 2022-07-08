package dev.jefvda.smartplanner.dayoverview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import dev.jefvda.smartplanner.R
import dev.jefvda.smartplanner.convertCalendarToTimeString
import dev.jefvda.smartplanner.database.Activity

class ActivityViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val activityNumberTextView: TextView = itemView.findViewById(R.id.activity_list_item_number)
    private val activityDescriptionTextView: TextView = itemView.findViewById(R.id.activity_list_item_description)
    private val activityStartTimeTextView: TextView = itemView.findViewById(R.id.activity_list_item_start_time)

    fun populateViewHolder(activity: Activity, position: Int) {
        activityNumberTextView.text = itemView.context.getString(R.string.activity_list_item_number, position + 1)
        activityDescriptionTextView.text = activity.description
        activityStartTimeTextView.text = convertCalendarToTimeString(activity.startTime)
    }
}
