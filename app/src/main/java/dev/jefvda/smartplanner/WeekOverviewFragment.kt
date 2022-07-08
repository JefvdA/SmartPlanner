package dev.jefvda.smartplanner

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.jefvda.smartplanner.databinding.FragmentWeekOverviewBinding
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WeekOverviewFragment : Fragment() {

    private var _binding: FragmentWeekOverviewBinding? = null

    private lateinit var weekdayListRecyclerView: RecyclerView
    private lateinit var weekdayListAdapter: WeekdayListAdapter

    private val calendar = getDateOfMondayInTwoWeeks(Calendar.getInstance())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekOverviewBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weekdayListRecyclerView = binding.weekdayListRecyclerView.let {
            it.layoutManager = LinearLayoutManager(binding.root.context)
            weekdayListAdapter = WeekdayListAdapter(getInitialWeekdays()) { weekday ->
                Log.d("WeekOverviewFragment", "You have clicked on ${weekday.day}")
            }
            it.adapter = weekdayListAdapter
            it
        }
    }

    override fun onStart() {
        super.onStart()

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.week_overview_toolbar_title, calendar.get(Calendar.WEEK_OF_YEAR))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getInitialWeekdays(): MutableList<Weekday> {
        val dayOfYearForMonday = calendar.get(Calendar.DAY_OF_YEAR)

        return mutableListOf(
            Weekday(getString(R.string.monday), dayOfYearForMonday),
            Weekday(getString(R.string.tuesday), dayOfYearForMonday + 1),
            Weekday(getString(R.string.wednesday), dayOfYearForMonday + 2),
            Weekday(getString(R.string.thursday), dayOfYearForMonday + 3),
            Weekday(getString(R.string.friday), dayOfYearForMonday + 4),
            Weekday(getString(R.string.saturday), dayOfYearForMonday + 5),
            Weekday(getString(R.string.sunday), dayOfYearForMonday + 6)
        )
    }
}