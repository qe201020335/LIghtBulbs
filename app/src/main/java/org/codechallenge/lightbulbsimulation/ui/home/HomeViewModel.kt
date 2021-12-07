package org.codechallenge.lightbulbsimulation.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Enter a number and pull some bulbs!"
    }
    val text: LiveData<String> = _text

    fun pull(input : String) {
        var numToPull: Int = 0;
        try {
            numToPull = input.toInt()
        } catch (e : NumberFormatException) {
            _text.apply { value = "Oops, this is not an integer." }
            return
        }

        if (numToPull <= 0) {
            _text.apply { value = "Too bad you did not get any bulbs." }
        } else if (numToPull > 70) {
            _text.apply { value = "There are only 70 bulbs!"}
        } else {
            _text.apply { value = input }
        }
    }
}