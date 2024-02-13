package com.udacity.shoestore.screens.details

import android.graphics.drawable.Drawable
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.InputType
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.screens.listings.ShoeListingsFragmentDirections
import java.io.InputStream
import kotlin.math.floor

class ShoeDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = ShoeDetailsFragment()
    }

    private lateinit var viewModel: ShoeDetailsViewModel
    private lateinit var viewModelFactory: ShoeDetailsViewModelFactory
    private val activityViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentShoeDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_details,
            container,
            false
        )

        binding.lifecycleOwner = this
        val args = ShoeDetailsFragmentArgs.fromBundle(requireArguments())
        var editEnable = false
        viewModelFactory = if (args.shoeId == -1) {
            editEnable = true
            ShoeDetailsViewModelFactory(Shoe("", 2.0, "", ""))

        } else {
            ShoeDetailsViewModelFactory(activityViewModel.shoesList.value!![args.shoeId])
        }

        viewModel = ViewModelProvider(this, viewModelFactory)[ShoeDetailsViewModel::class.java]
        if (editEnable){
            viewModel.setEditEnabled()
        }

        binding.detailsViewModel = viewModel

        viewModel.eventCancel.observe(this.viewLifecycleOwner, Observer { cancel ->
            if (cancel){
                onCancel()
                viewModel.onCancelEditComplete()
            }
        })

        viewModel.eventSave.observe(this.viewLifecycleOwner, Observer { save ->
            if (save){
                onSave()
                viewModel.onSaveComplete()
            }
        })

        if (viewModel.shoe.images.isNotEmpty() && viewModel.shoe.images.first().isNotEmpty()) {
            val ims: InputStream = requireContext().assets.open(viewModel.shoe.images.first())

            val d = Drawable.createFromStream(ims, null)
            val imageView = binding.imageView
            imageView.setImageDrawable(d)
            ims.close()
        }


        return binding.root
    }

    fun onCancel(){
        val action = ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListingsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    fun onSave(){
        viewModel.shoeEdit.value?.let { activityViewModel.addShoe(it) }
        val action = ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListingsFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}