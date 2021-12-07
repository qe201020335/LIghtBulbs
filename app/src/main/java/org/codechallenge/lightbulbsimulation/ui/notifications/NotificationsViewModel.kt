package org.codechallenge.lightbulbsimulation.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Sky Z \n @qe201020335"
    }
    val text: LiveData<String> = _text
}