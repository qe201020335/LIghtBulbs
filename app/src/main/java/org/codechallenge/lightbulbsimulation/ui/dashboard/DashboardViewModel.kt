package org.codechallenge.lightbulbsimulation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.codechallenge.lightbulbsimulation.utils.BulbCosntants
import org.codechallenge.lightbulbsimulation.utils.Simulation

class DashboardViewModel : ViewModel() {

    private val _result = MutableLiveData<String>().apply {
        value = ""
    }
    val result: LiveData<String> = _result

    private val _theory = MutableLiveData<String>().apply {
        value = ""
    }
    val theory: LiveData<String> = _theory

    private var numToPull: Int = 1
    private var maxSims : Int = 100


    fun runSims(numEachInput : String, numMaxInput : String) {
        try {
            numToPull = numEachInput.toInt()
            maxSims = numMaxInput.toInt()
        } catch (e : NumberFormatException) {
            _theory.apply { value = "Oops, not an integer." }
            return
        }
        if (!checkInputs()) {
            return
        }
        // calculate theory mean
        val theoryMean = Simulation.getInstance().calculateTheoryMean(numToPull);
        println("Theory Mean: $theoryMean")
        _theory.apply { value = "Theoretically, with $numToPull bulb each time, you will get ${"%.2f".format(theoryMean)} colors on average" }

        _result.apply { value = "Running simulations..." }
        // run simulations
        viewModelScope.launch(Dispatchers.Main) {
            _result.apply { value = Simulation.getInstance().runSimulations(numToPull, theoryMean, maxSims).toString()}
        }

    }

    private fun checkInputs() : Boolean {
        if (numToPull < 1) {
            _theory.apply { value = "At least one bulb each pull" }
            return false
        } else if (numToPull > BulbCosntants.TOTLE_NUMBER_BULB) {
            _theory.apply { value = "There are only ${BulbCosntants.TOTLE_NUMBER_BULB} bulbs!" }
            return false
        }
        if (maxSims < 1) {
            _theory.apply { value = "At least one simulation" }
            return false
        }
        return true
    }

}