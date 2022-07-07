package dev.jefvda.smartplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.jefvda.smartplanner.databinding.FragmentWeekOverviewBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WeekOverviewFragment : Fragment() {

    private var _binding: FragmentWeekOverviewBinding? = null

    private lateinit var weekdayListRecyclerView: RecyclerView

    private lateinit var weekdayListAdapter: WeekdayListAdapter

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
            weekdayListAdapter = WeekdayListAdapter(getInitialWeekdays())
            it.adapter = weekdayListAdapter
            it
        }
    }

    override fun onStart() {
        super.onStart()

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.week_overview_toolbar_title, 26)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getInitialWeekdays(): MutableList<Weekday> {
        return mutableListOf(
            Weekday(getString(R.string.monday), System.currentTimeMillis()),
            Weekday(getString(R.string.tuesday), System.currentTimeMillis()),
            Weekday(getString(R.string.wednesday), System.currentTimeMillis()),
            Weekday(getString(R.string.thursday), System.currentTimeMillis()),
            Weekday(getString(R.string.friday), System.currentTimeMillis()),
            Weekday(getString(R.string.saturday), System.currentTimeMillis()),
            Weekday(getString(R.string.sunday), System.currentTimeMillis())
        )
    }
}