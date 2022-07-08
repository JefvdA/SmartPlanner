package dev.jefvda.smartplanner.dayoverview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.jefvda.smartplanner.R
import dev.jefvda.smartplanner.convertCalendarToDateString
import dev.jefvda.smartplanner.database.Activity
import dev.jefvda.smartplanner.database.Weekday
import dev.jefvda.smartplanner.databinding.FragmentDayOverviewBinding
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DayOverviewFragment : Fragment() {

    private var _binding: FragmentDayOverviewBinding? = null
    private val args: DayOverviewFragmentArgs by navArgs()

    private lateinit var activityListRecyclerView: RecyclerView
    private lateinit var activityListAdapter: ActivityListAdapter

    private lateinit var weekday: Weekday

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDayOverviewBinding.inflate(inflater, container, false)
        weekday = args.weekday

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityListRecyclerView = binding.activityListRecyclerView.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            activityListAdapter = ActivityListAdapter(getInitialActivities())
            adapter = activityListAdapter
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.day_overview_toolbar_title, weekday.day, convertCalendarToDateString(weekday.calendar))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getInitialActivities(): MutableList<Activity> {
        return mutableListOf(
            Activity("App afwerken", Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 10)
                set(Calendar.MINUTE, 0)
            }),
            Activity("Summerschool drink", Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, 16)
                set(Calendar.MINUTE, 0)
            }),
        )
    }
}