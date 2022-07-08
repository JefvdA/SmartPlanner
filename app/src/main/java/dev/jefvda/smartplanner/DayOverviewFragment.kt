package dev.jefvda.smartplanner

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import dev.jefvda.smartplanner.databinding.FragmentDayOverviewBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DayOverviewFragment : Fragment() {

    private var _binding: FragmentDayOverviewBinding? = null
    private val args: DayOverviewFragmentArgs by navArgs()

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

        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.day_overview_toolbar_title, weekday.day, convertCalendarToDateString(weekday.calendar))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}