package dev.jefvda.smartplanner

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
