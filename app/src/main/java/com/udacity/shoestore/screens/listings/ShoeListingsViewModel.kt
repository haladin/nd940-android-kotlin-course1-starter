package com.udacity.shoestore.screens.listings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListingsViewModel : ViewModel() {

    private val _eventOpenDetails = MutableLiveData<Boolean>()
    val eventOpenDetails: LiveData<Boolean>
        get() = _eventOpenDetails

    init {
        _eventOpenDetails.value = false
    }

    fun continueToDetails() {
        _eventOpenDetails.value = true
    }

    fun onDetailsOpenComplete() {
        _eventOpenDetails.value = false
    }
}