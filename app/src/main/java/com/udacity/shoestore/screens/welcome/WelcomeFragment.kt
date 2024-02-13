package com.udacity.shoestore.screens.welcome

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeBinding
    private val viewModel: WelcomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false)

        binding.welcomeViewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.eventContinue.observe(this.viewLifecycleOwner, Observer { cont ->
            if (cont){
                onContinue()
                viewModel.onContinueComplete()
            }
        })

        return binding.root
    }

    private fun onContinue(){
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToInstructionsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}