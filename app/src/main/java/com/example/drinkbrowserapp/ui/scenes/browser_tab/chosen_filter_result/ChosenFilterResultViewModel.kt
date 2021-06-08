package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChosenFilterResultViewModel @Inject constructor(
    private val repository: FilterResultRepository,
    private val drinkName: String, private val filterName: String
) : ViewModel(){

}