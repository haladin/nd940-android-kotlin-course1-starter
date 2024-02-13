package com.udacity.shoestore.screens.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false)

        binding.loginViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventLogin.observe(this.viewLifecycleOwner, Observer { login ->
            if (login){
                onLogin()
                viewModel.onLoginComplete()
            }
        })

        viewModel.eventSignIn.observe(this.viewLifecycleOwner, Observer { signIn ->
            if (signIn){
                onSignIn()
                viewModel.onSignInComplete()
            }
        })

        return binding.root
    }

    private fun onLogin(){
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        findNavController(this).navigate(action)
    }

    private fun onSignIn(){
        val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
        findNavController(this).navigate(action)
    }
}