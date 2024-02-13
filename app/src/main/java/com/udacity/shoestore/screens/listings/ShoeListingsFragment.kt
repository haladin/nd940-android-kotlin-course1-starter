package com.udacity.shoestore.screens.listings

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.view.ViewGroup.MarginLayoutParams
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Space
import android.widget.TextView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.card.MaterialCardView
import com.udacity.shoestore.MainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListingsBinding
import com.udacity.shoestore.models.Shoe
import java.io.InputStream


class ShoeListingsFragment : Fragment() {

    private lateinit var binding: FragmentShoeListingsBinding
    private val viewModel: ShoeListingsViewModel by viewModels()
    private val activityViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_listings,
            container,
            false
        )
        binding.shoeListingViewModel = viewModel

        viewModel.eventOpenDetails.observe(this.viewLifecycleOwner, Observer { details ->
            if (details){
                onDetails(-1)
                viewModel.onDetailsOpenComplete()
            }
        })

        for ((index, shoe) in activityViewModel.shoesList.value!!.withIndex()) {
            binding.mainLayout.addView(createItem(index, shoe))
        }

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.listings_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.logout -> {
                        onLogout()
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

        return binding.root
    }

    fun onLogout(){
        val action = ShoeListingsFragmentDirections.actionShoeListingsFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

    fun onDetails(shoeId: Int){
        val action = ShoeListingsFragmentDirections.actionShoeListingsFragmentToShoeDetailsFragment(shoeId)
        NavHostFragment.findNavController(this).navigate(action)
    }

    @SuppressLint("SetTextI18n")
    fun createItem(index:Int, shoe: Shoe): View?{

        context?.let {
            val paramsCard = MarginLayoutParams(resources.getDimension(R.dimen.card_width).toInt(), LayoutParams.MATCH_PARENT)
            paramsCard.topMargin = resources.getDimension(R.dimen.card_top_margin).toInt()
            val layout = LinearLayout(it)
            layout.layoutParams = paramsCard
            layout.orientation = LinearLayout.VERTICAL
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                layout.setBackgroundColor(it.getColor(R.color.md_theme_light_surface))
            }

            layout.setOnClickListener{
                onDetails(index)
            }

            val imageView = ImageView(it)
            imageView.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, resources.getDimension(R.dimen.card_image_height).toInt())
            var filename = "250x250.png"
            if (shoe.images.isNotEmpty() && shoe.images.first().isNotEmpty()) {
                filename = shoe.images.first()
            }
            val ims: InputStream = requireContext().assets.open(filename)
            val d = Drawable.createFromStream(ims, null)
            imageView.setImageDrawable(d)
            ims.close()

            layout.addView(imageView)

            val titleTextView = TextView(it)
            titleTextView.text = shoe.name
            titleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,24.0F)

            val titleTextParams = MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            titleTextParams.topMargin = resources.getDimension(R.dimen.card_subtitle_margin).toInt()
            titleTextParams.marginStart = resources.getDimension(R.dimen.card_subtitle_margin_start).toInt()
            titleTextView.layoutParams = titleTextParams

            layout.addView(titleTextView)

            val companyTextView = TextView(it)
            companyTextView.text = shoe.company
            companyTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0F)
            val params = MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            params.topMargin = resources.getDimension(R.dimen.card_subtitle_margin).toInt()
            params.bottomMargin = resources.getDimension(R.dimen.card_subtitle_margin).toInt()
            params.marginStart = resources.getDimension(R.dimen.card_subtitle_margin_start).toInt()
            companyTextView.layoutParams = params

            layout.addView(companyTextView)

            val sizeTextView = TextView(it)
            sizeTextView.text = "Size: ${shoe.size.toInt()}"
            sizeTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP,14.0F)
            val paramsSize = MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
            paramsSize.topMargin = resources.getDimension(R.dimen.card_subtitle_margin).toInt()
            paramsSize.bottomMargin = resources.getDimension(R.dimen.card_subtitle_margin).toInt()
            paramsSize.marginStart = resources.getDimension(R.dimen.card_subtitle_margin_start).toInt()
            sizeTextView.layoutParams = paramsSize

            layout.addView(sizeTextView)

            return layout
        }

        return null
    }
}