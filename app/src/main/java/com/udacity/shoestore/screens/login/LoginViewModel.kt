package com.udacity.shoestore.screens.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    val email = MutableLiveData<String>()

    val password = MutableLiveData<String>()

    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    private val _eventSignIn = MutableLiveData<Boolean>()
    val eventSignIn: LiveData<Boolean>
        get() = _eventSignIn

    init {
        _eventLogin.value = false
        _eventLogin.value = false
        email.value = ""
        password.value = ""
    }

    fun login() {
        _eventLogin.value = true
    }

    fun onLoginComplete() {
        _eventLogin.value = false
    }

    fun singIn() {
        _eventSignIn.value = true
    }

    fun onSignInComplete() {
        _eventSignIn.value = false
    }
}