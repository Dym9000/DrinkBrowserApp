package com.example.drinkbrowserapp.ui.scenes.browser_tab.filters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.domain.FilterDomain
import com.example.drinkbrowserapp.domain.FilterDomainCriteria
import com.example.drinkbrowserapp.util.Constants
import com.example.drinkbrowserapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FiltersViewModel @Inject constructor(filtersRepository: FiltersRepository) : ViewModel() {

    private val requestFiltersResults = MediatorLiveData<DataState<List<FilterDomainCriteria>>>()

    private val ingredients = filtersRepository.getIngredients(Constants.API_TOKEN_KEY, Constants.FILTERS_QUERY)

    private var _filters = MutableLiveData<List<FilterDomain>>()
    val filters: LiveData<List<FilterDomain>>
    get(){
        return _filters
    }

    private val mfiltersCurrentList = mutableListOf<FilterDomain>()

    init{
        setFiltersSources()
    }

    private fun setFiltersSources(){

        requestFiltersResults.addSource(ingredients){
            mfiltersCurrentList.add(FilterDomain(Constants.INGREDIENT, it))
            _filters.value = mfiltersCurrentList
        }
        requestFiltersResults.removeSource(ingredients)
    }
}