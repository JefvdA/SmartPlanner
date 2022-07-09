package dev.jefvda.smartplanner.weekoverview

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.jefvda.smartplanner.R
import dev.jefvda.smartplanner.database.SmartPlannerDatabase
import dev.jefvda.smartplanner.database.Weekday
import dev.jefvda.smartplanner.databinding.FragmentWeekOverviewBinding
import dev.jefvda.smartplanner.dayoverview.ActivityListViewModel
import dev.jefvda.smartplanner.dayoverview.ActivityListViewModelFactory
import dev.jefvda.smartplanner.getDateOfMondayInTwoWeeks
import java.lang.Exception
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class WeekOverviewFragment : Fragment() {

    private var _binding: FragmentWeekOverviewBinding? = null

    private lateinit var activityListViewModel: ActivityListViewModel

    private lateinit var weekdayListRecyclerView: RecyclerView
    private lateinit var weekdayListAdapter: WeekdayListAdapter

    private lateinit var generateEmailFAB: FloatingActionButton

    private lateinit var clearActivitiesButton: Button

    private val calendar = getDateOfMondayInTwoWeeks(Calendar.getInstance())

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWeekOverviewBinding.inflate(inflater, container, false)

        val viewModelFactory = ActivityListViewModelFactory(SmartPlannerDatabase.getInstance(this.requireContext()).activityDao)
        activityListViewModel = ViewModelProvider(this, viewModelFactory)[ActivityListViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        weekdayListRecyclerView = binding.weekdayListRecyclerView.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            weekdayListAdapter = WeekdayListAdapter(getInitialWeekdays()) { weekday ->
                navigateToDayOverview(weekday)
            }
            adapter = weekdayListAdapter
        }

        generateEmailFAB = binding.generateEmailFAB.apply {
            setOnClickListener {
                generateEmail()
            }
        }

        clearActivitiesButton = binding.clearActivitiesButton.apply {
            setOnClickListener {
                activityListViewModel.clearActivities()
            }
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

    private fun navigateToDayOverview(weekday: Weekday) {
        val action =
            WeekOverviewFragmentDirections.actionWeekOverviewFragmentToDayOverviewFragment(
                weekday
            )
        findNavController().navigate(action)
    }

    private fun generateEmail() {
        val toAddress = getString(R.string.email_to_address)
        val subject = getString(R.string.email_subject, calendar.get(Calendar.WEEK_OF_YEAR))
        val body = getString(R.string.email_body, calendar.get(Calendar.WEEK_OF_YEAR))

        val urlString = "mailto:${Uri.encode(toAddress)}?subject=${Uri.encode(subject)}&body=${Uri.encode(body)}"
        val selectorIntent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse(urlString)
        }

        val mailIntent = Intent(Intent.ACTION_SEND).apply {
            putExtra(Intent.EXTRA_EMAIL, arrayOf(toAddress))
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT, body)
            selector = selectorIntent
        }

        try {
            startActivity(Intent.createChooser(mailIntent, "Choose a mail app"))
        } catch (e: Exception) {
            Toast.makeText(binding.root.context, "There was a problem with opening a mail app", Toast.LENGTH_LONG).show()
        }
    }
}