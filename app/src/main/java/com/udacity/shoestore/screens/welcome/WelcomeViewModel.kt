package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class WelcomeViewModel : ViewModel() {

    private val _eventContinue = MutableLiveData<Boolean>()
    val eventContinue: LiveData<Boolean>
        get() = _eventContinue

    init {
        _eventContinue.value = false
    }

    fun continueToInstructions() {
        _eventContinue.value = true
    }

    fun onContinueComplete() {
        _eventContinue.value = false
    }
}