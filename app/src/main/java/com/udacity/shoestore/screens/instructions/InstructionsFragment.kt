package com.udacity.shoestore.screens.instructions

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
import com.udacity.shoestore.databinding.FragmentInstructionsBinding
import com.udacity.shoestore.screens.welcome.WelcomeFragmentDirections
import com.udacity.shoestore.screens.welcome.WelcomeViewModel

class InstructionsFragment : Fragment() {

    private lateinit var binding: FragmentInstructionsBinding
    private val viewModel: InstructionsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_instructions,
            container,
            false)

        binding.instructionsViewModel = viewModel
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
        val action = InstructionsFragmentDirections.actionInstructionsFragmentToShoeListingsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}