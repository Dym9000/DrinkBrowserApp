package com.example.drinkbrowserapp.ui.search

import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.repository.DrinkRepositoryOld
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val drinkRepositoryOld: DrinkRepositoryOld) :
    ViewModel() {

//    private val _drinkNameQuery = MutableLiveData<String?>()
//    val drinkNameQuery: LiveData<String?>
//        get() = _drinkNameQuery
//
//    var mSearchFilter: SearchFilter = SearchFilter.DRINK
//
//    val drinksByName: LiveData<DataState<List<DrinkDb>>> = Transformations
//        .switchMap(drinkNameQuery) {
//            it?.let {
//                drinkRepository.getDrinksByName(it, Constants.API_TOKEN_KEY)
//            }
//        }
//
//    val drinksByIngredient: LiveData<DataState<List<FilterSearchRaw>>> = Transformations
//        .switchMap(drinkNameQuery) {
//            it?.let {
//                drinkRepository.getDrinksByIngredient(it, Constants.API_TOKEN_KEY)
//            }
//        }
//
//    fun setDrinkNameQuery(query: String?) {
//        if (query != _drinkNameQuery.value)
//            _drinkNameQuery.value = query
//    }
//
//    fun onFilterClick(filter: String) {
//        when (filter) {
//            "drink" -> mSearchFilter = SearchFilter.DRINK
//            "ingredient" -> mSearchFilter = SearchFilter.INGREDIENT
//        }
//    }

}