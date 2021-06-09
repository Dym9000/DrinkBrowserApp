package com.example.drinkbrowserapp.ui.scenes.common.drink_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
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
            inflater, R.layout.fragment_drink_details, container, false
        )

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        drinkDetailsBinding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = drinkDetailsViewModel
        }

        setObservers()

        return drinkDetailsBinding.root
    }

    private fun setObservers(){
        drinkDetailsViewModel.drinkDetailsList.observe(viewLifecycleOwner,{
            if(!it.data.isNullOrEmpty()){
                drinkDetailsViewModel.onDataFetched(it.data[0])
            }
        })
    }

}