package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FilterResultViewModelFactory(
    private val repository: FilterResultRepository,
    private val drinkName: String?,
    private val filterName: String?
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChosenFilterResultViewModel::class.java)) {

            return drinkName?.let {
                if (filterName != null) {
                    ChosenFilterResultViewModel(repository, it, filterName)
                }
            } as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}