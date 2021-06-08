package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.util.Constants

class ChosenFilterResultViewModel (
    private val repository: FilterResultRepository,
    private val itemName: String, private val filterName: String
) : ViewModel(){

    val ingredients = repository.getIngredients(Constants.API_TOKEN_KEY, itemName)

    val randomString = "THIS IS VIEW MODEL"
}