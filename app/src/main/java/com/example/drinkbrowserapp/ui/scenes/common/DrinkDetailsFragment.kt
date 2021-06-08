package com.example.drinkbrowserapp.ui.scenes.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.FragmentDrinkDetailsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DrinkDetailsFragment : Fragment() {

    @Inject
    lateinit var drinkRepository: DrinkRepository

    private lateinit var drinkDetailsBinding: FragmentDrinkDetailsBinding

    private var requestManager: RequestManager? = null

    private val drinkDetailsArgs: DrinkDetailsFragmentArgs by navArgs()

    private val drinkDetailsViewModel: DrinkDetailsViewModel by viewModels {
        DrinkDetailsViewModelFactory(drinkDetailsArgs.drinkId, drinkRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        drinkDetailsBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_drink_details, container, false)

        drinkDetailsBinding.lifecycleOwner = viewLifecycleOwner

        setGlide()

        return drinkDetailsBinding.root
    }

    private fun setGlide() {
        val requestOptions = RequestOptions
            .placeholderOf(R.drawable.ic_baseline_hourglass_top_24)
            .error(R.drawable.ic_baseline_no_drinks_24)

        activity?.let {
            requestManager = Glide.with(it)
                .applyDefaultRequestOptions(requestOptions)
        }
    }

}