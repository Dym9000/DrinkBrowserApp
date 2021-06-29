package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.drinkbrowserapp.ui.common.repository.DrinkRepository

class FilterResultViewModelFactory(
    private val repository: FilterResultRepository,
    private val itemName: String?,
    private val filterName: String?
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChosenFilterResultViewModel::class.java)
            && itemName != null && filterName != null
        ) {
            return ChosenFilterResultViewModel(repository, itemName, filterName) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}