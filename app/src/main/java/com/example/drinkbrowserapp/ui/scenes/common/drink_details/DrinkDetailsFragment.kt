package com.example.drinkbrowserapp.ui.scenes.common.drink_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.FragmentDrinkDetailsBinding
import com.example.drinkbrowserapp.ui.common.UIStateListener
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DrinkDetailsFragment : Fragment() {

    @Inject
    lateinit var drinkRepository: DrinkRepository

    private lateinit var drinkDetailsBinding: FragmentDrinkDetailsBinding

    private val drinkDetailsArgs: DrinkDetailsFragmentArgs by navArgs()

    private val drinkDetailsViewModel: DrinkDetailsViewModel by viewModels {
        DrinkDetailsViewModelFactory(drinkDetailsArgs.drinkId, drinkRepository)
    }

    private lateinit var drinkDetailsStateListener: UIStateListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        drinkDetailsStateListener = context as UIStateListener
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

    private fun setObservers() {
        drinkDetailsViewModel.drinkDetailsList.observe(viewLifecycleOwner, {
            drinkDetailsStateListener.onDataStateChanged(it)
            if (!it.data.isNullOrEmpty()) {
                drinkDetailsViewModel.onDataFetched(it.data[0])
            }
        })
    }

}