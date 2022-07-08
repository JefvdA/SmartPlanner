package dev.jefvda.smartplanner.dayoverview

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.jefvda.smartplanner.R
import dev.jefvda.smartplanner.convertCalendarToDateString
import dev.jefvda.smartplanner.database.ActivityEntity
import dev.jefvda.smartplanner.database.SmartPlannerDatabase
import dev.jefvda.smartplanner.database.Weekday
import dev.jefvda.smartplanner.databinding.FragmentDayOverviewBinding
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DayOverviewFragment : Fragment() {

    private var _binding: FragmentDayOverviewBinding? = null
    private val args: DayOverviewFragmentArgs by navArgs()

    private lateinit var activityListViewModel: ActivityListViewModel

    private lateinit var activityListRecyclerView: RecyclerView
    private lateinit var activityListAdapter: ActivityListAdapter

    private lateinit var addActivityFAB: FloatingActionButton

    private lateinit var weekday: Weekday

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        private val TAG = DayOverviewFragment::class.java.simpleName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDayOverviewBinding.inflate(inflater, container, false)
        weekday = args.weekday

        val viewModelFactory = ActivityListViewModelFactory(SmartPlannerDatabase.getInstance(this.requireContext()).activityDao)
        activityListViewModel = ViewModelProvider(this, viewModelFactory)[ActivityListViewModel::class.java]
        activityListViewModel.activityList.observe(this.viewLifecycleOwner) {
            Log.d("DayOverviewFragment", "activity list updated")
            activityListAdapter.activityList = it
            activityListAdapter.notifyDataSetChanged()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityListRecyclerView = binding.activityListRecyclerView.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            activityListAdapter = ActivityListAdapter(mutableListOf())
            adapter = activityListAdapter
        }

        addActivityFAB = binding.addActivityFAB.apply {
            setOnClickListener {
                showAddActivityDialog()
            }
        }

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.day_overview_toolbar_title, weekday.day, convertCalendarToDateString(weekday.calendar))
    }

    private fun showAddActivityDialog() {
        val addActivityEditText = EditText(binding.root.context).apply {
            inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_WORDS
        }

        AlertDialog.Builder(binding.root.context)
            .setTitle("Create activity")
            .setMessage("Give a description for the new activity")
            .setView(addActivityEditText)
            .setPositiveButton("Create") { dialogInterface: DialogInterface, _: Int ->
                val newActivityDescription = addActivityEditText.text.toString()
                activityListViewModel.saveActivity(ActivityEntity(0L, newActivityDescription, Calendar.getInstance(), weekday.day))
                Log.d(TAG, "Creating new activity... ($newActivityDescription)")
                dialogInterface.dismiss()
            }
            .setNegativeButton("Cancel") { dialogInterface: DialogInterface, _: Int ->
                dialogInterface.dismiss()
            }
            .create()
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}