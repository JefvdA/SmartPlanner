package dev.jefvda.smartplanner.dayoverview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.jefvda.smartplanner.R
import dev.jefvda.smartplanner.database.ActivityEntity

class ActivityListAdapter(var activityList: MutableList<ActivityEntity>): RecyclerView.Adapter<ActivityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        return ActivityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_list_item, parent, false))
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.populateViewHolder(activityList[position], position)
    }

    override fun getItemCount(): Int {
        return activityList.size
    }
}