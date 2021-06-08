package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.drinkbrowserapp.R
import com.example.drinkbrowserapp.databinding.FragmentDisplayListBinding
import com.example.drinkbrowserapp.util.Constants

//@AndroidEntryPoint
class ChosenFilterResultFragment: Fragment() {

    private lateinit var filterResultBinding: FragmentDisplayListBinding
    private val filterResultArgs: ChosenFilterResultFragmentArgs by navArgs()
    private lateinit var ingredientResultAdapter: IngredientResultAdapter
    private lateinit var filterResultAdapter: FilterResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        filterResultBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_display_list, container, false)

        filterResultBinding.apply {
            lifecycleOwner = viewLifecycleOwner
        }

        if(filterResultArgs.filterName == Constants.INGREDIENT){
            TODO("set ingredient result adapter")
        }else{
            TODO("set filter result adapter")
        }

            return filterResultBinding.root
        }
}