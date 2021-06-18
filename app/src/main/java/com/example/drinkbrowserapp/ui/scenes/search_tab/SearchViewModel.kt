package com.example.drinkbrowserapp.ui.scenes.search_tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.drinkbrowserapp.domain.DrinkDomain
import com.example.drinkbrowserapp.ui.scenes.common.drink_details.DrinkRepository
import com.example.drinkbrowserapp.util.Constants
import com.example.drinkbrowserapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(drinkRepository: DrinkRepository) : ViewModel() {

    private val _query = MutableLiveData<String>()
    private val query: LiveData<String> = _query

    val searchResult: LiveData<DataState<List<DrinkDomain>>> = query.switchMap {
        drinkRepository.getDrinksByName(Constants.API_TOKEN_KEY, it)
    }

    fun setQuery(query: String){
        _query.value = query
    }
}