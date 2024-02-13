package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.DataGenerator
import com.udacity.shoestore.models.DataGenerator.Companion
import com.udacity.shoestore.models.Shoe

class MainViewModel : ViewModel() {

    var shoesList: LiveData<MutableList<Shoe>> = MutableLiveData(DataGenerator.getData())
    var companyNames: LiveData<List<String>> = MutableLiveData(Companion.shoeCompanies)


    fun addShoe(shoe: Shoe){
        shoesList.value?.add(0, shoe)
    }
}