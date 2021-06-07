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

    private val ingredients =
        filtersRepository.getIngredients(Constants.API_TOKEN_KEY, Constants.FILTERS_QUERY)
    private var mAreIngredientsFetched = false

    private val categories =
        filtersRepository.getCategories(Constants.API_TOKEN_KEY, Constants.FILTERS_QUERY)
    private var mAreCategoriesFetched = false

    private val glasses =
        filtersRepository.getGlasses(Constants.API_TOKEN_KEY, Constants.FILTERS_QUERY)
    private var mAreGlassesFetched = false

    private val alcoholContents =
        filtersRepository.getAlcoholContents(Constants.API_TOKEN_KEY, Constants.FILTERS_QUERY)
    private var mAreAlcoholContentsFetched = false

    private val mFiltersCurrentList = mutableListOf<FilterDomain>()

    val filters = MediatorLiveData<List<FilterDomain>>()

    private val _itemClickedName = MutableLiveData<Map<String, String>>()
    val itemClickedName:LiveData<Map<String,String>>
    get(){
        return _itemClickedName
    }

    init {
        setFiltersSources()
    }

    private fun setFiltersSources() {

        // adding ingredients as a data source

        filters.addSource(ingredients) {
            if (!it.data.isNullOrEmpty()) {
                mAreIngredientsFetched = true
                addToList(Constants.INGREDIENT, it)
            } else {
                filters.value = listOf(FilterDomain(Constants.LOADING, it))
            }
        }

        // adding categories as a data source

        filters.addSource(categories) {
            if (!it.data.isNullOrEmpty()) {
                mAreCategoriesFetched = true
                addToList(Constants.CATEGORY, it)
            } else {
                filters.value = listOf(FilterDomain(Constants.LOADING, it))
            }
        }

        // adding glasses as a data source

        filters.addSource(glasses) {
            if (!it.data.isNullOrEmpty()) {
                mAreGlassesFetched = true
                addToList(Constants.GLASS, it)
            } else {
                filters.value = listOf(FilterDomain(Constants.LOADING, it))
            }
        }

        // adding alcohol contents as a data source

        filters.addSource(alcoholContents) {
            if (!it.data.isNullOrEmpty()) {
                mAreAlcoholContentsFetched = true
                addToList(Constants.ALCOHOL_CONTENT, it)
            } else {
                filters.value = listOf(FilterDomain(Constants.LOADING, it))
            }
        }

    }

    private fun addToList(title: String, newList: DataState<List<FilterDomainCriteria>>) {
        mFiltersCurrentList.add(FilterDomain(title, newList))
        mFiltersCurrentList.sortBy {
            it.name
        }
        if (mAreIngredientsFetched && mAreCategoriesFetched && mAreGlassesFetched
            && mAreAlcoholContentsFetched
        ) {
            filters.value = mFiltersCurrentList
        }
    }

    fun onItemClicked(filterTitle: String, item:FilterDomainCriteria){
        _itemClickedName.value = mapOf(pair = Pair(filterTitle, item.name))
    }
}