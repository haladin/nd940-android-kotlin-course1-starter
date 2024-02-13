package com.udacity.shoestore.screens.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeDetailsViewModel(val shoe: Shoe) : ViewModel() {

    private val _enabled = MutableLiveData<Boolean>()
    val enabled: LiveData<Boolean>
        get() = _enabled

    val shoeEdit = MutableLiveData<Shoe>()

    private val _eventSave = MutableLiveData<Boolean>()
    val eventSave: LiveData<Boolean>
        get() = _eventSave

    private val _eventCancel = MutableLiveData<Boolean>()
    val eventCancel: LiveData<Boolean>
        get() = _eventCancel

    init {
        shoeEdit.value = shoe
        _eventCancel.value = false
        _eventSave.value = false
    }

    fun setEditDisabled(){
        _enabled.value = false
    }

    fun setEditEnabled(){
        _enabled.value = true
    }

    fun saveShoe() {
        _eventSave.value = true
    }

    fun onSaveComplete() {
        _eventSave.value = false
    }

    fun cancelEdit() {
        _eventCancel.value = true
    }

    fun onCancelEditComplete() {
        _eventCancel.value = false
    }

}