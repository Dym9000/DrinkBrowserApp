package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.util.Constants

class ChosenFilterResultViewModel(
    private val repository: FilterResultRepository,
    private val itemName: String,
    private val filterName: String
) : ViewModel() {

    val drinksByFilter = repository.getDrinksByFilter(Constants.API_TOKEN_KEY, itemName, filterName)

    private val _drinkId = MutableLiveData<Int>()
    val drinkId: LiveData<Int>
        get() {
            return _drinkId
        }

    fun onClick(id: Int) {
        _drinkId.value = id
    }

    fun onNavigated() {
        _drinkId.value = -1
    }

}