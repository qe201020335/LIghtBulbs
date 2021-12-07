package org.codechallenge.lightbulbsimulation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _title = MutableLiveData<String>().apply {
        value = "Time to pull some bulbs."
    }
    val title: LiveData<String> = _title

    private val _askNum = MutableLiveData<String>().apply {
        value = "How many do you want?"
    }
    val askNum: LiveData<String> = _askNum
}