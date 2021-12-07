package org.codechallenge.lightbulbsimulation.ui.home

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
import org.codechallenge.lightbulbsimulation.databinding.FragmentHomeBinding
import org.codechallenge.lightbulbsimulation.utils.BulbCosntants

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.result
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        val numInput: EditText = binding.editTextNumber
        numInput.setText("0")

        val totalNumberText = getString(R.string.total_number, BulbCosntants.TOTLE_NUMBER_BULB)
//        println(totalNumberText)
        val totalNumberTextView: TextView = binding.textTotalNumber
        totalNumberTextView.text = totalNumberText

        val pullButton: Button = binding.pull
        pullButton.setOnClickListener{
            homeViewModel.pull(numInput.text.toString())
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}