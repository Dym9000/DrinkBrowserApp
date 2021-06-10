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

    /**
     *  Fetching data from repository and setting the flags to true when data is fetched
     */
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

    /**
     *  Mutable list gathering results from every query
     */
    private val mFiltersCurrentList = mutableListOf<FilterDomain>()

    /**
     *  Observable list with results from queries
     */
    val filters = MediatorLiveData<List<FilterDomain>>()

    /**
     *  Name of the clicked item
     */
    private val _itemClickedName = MutableLiveData<String?>()
    val itemClickedName: LiveData<String?>
        get() {
            return _itemClickedName
        }

    /**
     *  Name of the filter in which the item is
     */
    var mItemClickedFilterName: String? = null


    /**
     *  Setting sources for the filters MediatorLiveData observable list
     */
    init {
        setFiltersSources()
    }

    /**
     *  Observable state of the queries' results. Set when the state of the data is LOADING or
     *  ERROR and also when all the data is fetched
     */
    private val _dataState = MutableLiveData<DataState<List<FilterDomainCriteria>>>()
    val dataState: LiveData<DataState<List<FilterDomainCriteria>>> = _dataState

    private fun setFiltersSources() {

        // adding ingredients as a data source

        filters.addSource(ingredients) {
            if (!it.data.isNullOrEmpty()) {
                mAreIngredientsFetched = true
                addToList(Constants.INGREDIENT, it)
            } else {
                _dataState.value = it
            }
        }

        // adding categories as a data source

        filters.addSource(categories) {
            if (!it.data.isNullOrEmpty()) {
                mAreCategoriesFetched = true
                addToList(Constants.CATEGORY, it)
            } else {
                _dataState.value = it
            }
        }

        // adding glasses as a data source

        filters.addSource(glasses) {
            if (!it.data.isNullOrEmpty()) {
                mAreGlassesFetched = true
                addToList(Constants.GLASS, it)
            } else {
                _dataState.value = it
            }
        }

        // adding alcohol contents as a data source

        filters.addSource(alcoholContents) {
            if (!it.data.isNullOrEmpty()) {
                mAreAlcoholContentsFetched = true
                addToList(Constants.ALCOHOL_CONTENT, it)
            } else {
                _dataState.value = it
            }
        }

    }

    /**
     *  Adding queries' results to the mutable list and setting the observable one only if all the
     *  queries are finished
     */
    private fun addToList(title: String, newList: DataState<List<FilterDomainCriteria>>) {
        mFiltersCurrentList.add(FilterDomain(title, newList))
        mFiltersCurrentList.sortBy {
            it.name
        }
        if (mAreIngredientsFetched && mAreCategoriesFetched && mAreGlassesFetched
            && mAreAlcoholContentsFetched
        ) {
            filters.value = mFiltersCurrentList
            _dataState.value = newList
        }
    }

    /**
     *  When user clicks an item, filter's name and item's name are set and sent to the next
     *  fragment
     */
    fun onItemClicked(filterTitle: String, item: FilterDomainCriteria) {
        mItemClickedFilterName = filterTitle
        _itemClickedName.value = item.name
    }

    /**
     *  Setting to null the names of the filter and item to prevent from bugs when clicking the
     *  back button while in the next fragment
     */
    fun navigatedToClickedItem() {
        mItemClickedFilterName = null
        _itemClickedName.value = null
        resetFetchedDataFlags()
    }

    /**
     *  Setting fetched data flags to false
     */
    private fun resetFetchedDataFlags(){
        mAreIngredientsFetched = false
        mAreAlcoholContentsFetched = false
        mAreGlassesFetched = false
        mAreCategoriesFetched = false
    }
}