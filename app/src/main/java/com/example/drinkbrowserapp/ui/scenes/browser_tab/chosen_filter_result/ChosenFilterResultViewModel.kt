package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.util.Constants

class ChosenFilterResultViewModel(
    repository: FilterResultRepository,
    itemName: String, filterName: String
) : ViewModel() {

    val drinksByFilter = repository.getDrinksByFilter(Constants.API_TOKEN_KEY, itemName, filterName)

}