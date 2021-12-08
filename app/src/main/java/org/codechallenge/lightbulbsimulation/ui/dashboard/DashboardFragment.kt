package org.codechallenge.lightbulbsimulation.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import org.codechallenge.lightbulbsimulation.R
import org.codechallenge.lightbulbsimulation.databinding.FragmentDashboardBinding
import org.codechallenge.lightbulbsimulation.utils.BulbCosntants

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this)[DashboardViewModel::class.java]

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val resultView: TextView = binding.result
        dashboardViewModel.result.observe(viewLifecycleOwner, Observer {
            resultView.text = it
        })

        val theoryView: TextView = binding.theory
        dashboardViewModel.theory.observe(viewLifecycleOwner, Observer {
            theoryView.text = it
        })


        val numInput: EditText = binding.editTextNumber
        numInput.setText("1")
        val maxSims: EditText = binding.textMaxSims
        maxSims.setText("1")

        val totalNumberText = getString(R.string.total_number, BulbCosntants.TOTAL_NUMBER_BULB)
        val totalNumberTextView: TextView = binding.textTotalNumber
        totalNumberTextView.text = totalNumberText

        val pullButton: Button = binding.pull
        pullButton.setOnClickListener{
            dashboardViewModel.runSims(numEachInput = numInput.text.toString(), numMaxInput = maxSims.text.toString())
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}