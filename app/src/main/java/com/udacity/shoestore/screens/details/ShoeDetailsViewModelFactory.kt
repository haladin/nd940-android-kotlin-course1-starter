package com.udacity.shoestore.screens.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.udacity.shoestore.models.Shoe

class ShoeDetailsViewModelFactory(private val shoe: Shoe) : ViewModelProvider.Factory{
    public override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ShoeDetailsViewModel::class.java)) {
            return ShoeDetailsViewModel(shoe) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }


}