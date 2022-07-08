package dev.jefvda.smartplanner

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ActivityListAdapter(var activityList: MutableList<Activity>): RecyclerView.Adapter<ActivityViewHolder>() {

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