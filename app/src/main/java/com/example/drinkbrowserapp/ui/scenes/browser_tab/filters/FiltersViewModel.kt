package com.example.drinkbrowserapp.ui.scenes.browser_tab.filters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.domain.FilterDomain
import com.example.drinkbrowserapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(filtersRepository: FiltersRepository) : ViewModel() {

    val ingredients = filtersRepository.getIngredients(Constants.API_TOKEN_KEY, Constants.FILTERS_QUERY)

    private fun updateList(){

    }

    private var _filters = MutableLiveData<List<FilterDomain>>()
    val filters: LiveData<List<FilterDomain>>
    get(){
        return _filters
    }

    private val mfiltersCurrentList = mutableListOf<FilterDomain>()


}