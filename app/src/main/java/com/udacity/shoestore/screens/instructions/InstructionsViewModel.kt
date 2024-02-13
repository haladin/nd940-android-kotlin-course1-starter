package com.udacity.shoestore.screens.instructions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class InstructionsViewModel : ViewModel() {

    private val _eventContinue = MutableLiveData<Boolean>()
    val eventContinue: LiveData<Boolean>
        get() = _eventContinue

    init {
        _eventContinue.value = false
    }

    fun continueToListing() {
        _eventContinue.value = true
    }

    fun onContinueComplete() {
        _eventContinue.value = false
    }
}