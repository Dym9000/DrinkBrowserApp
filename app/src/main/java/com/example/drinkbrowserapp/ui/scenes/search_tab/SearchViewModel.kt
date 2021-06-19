package com.example.drinkbrowserapp.ui.scenes.search_tab

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.drinkbrowserapp.domain.DrinkDomain
import com.example.drinkbrowserapp.ui.scenes.common.drink_details.DrinkRepository
import com.example.drinkbrowserapp.util.Constants
import com.example.drinkbrowserapp.util.DataState
import com.example.drinkbrowserapp.util.PreferenceKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val drinkRepository: DrinkRepository,
    private val sharedPreferences: SharedPreferences,
    private val sharedPrefEditor: SharedPreferences.Editor
) : ViewModel() {

    private var currentFilter: String? = sharedPreferences.getString(PreferenceKeys.SEARCH, Constants.NAME)

    private val _query = MutableLiveData<String>()
    private val query: LiveData<String> = _query

    val searchResult: LiveData<DataState<List<DrinkDomain>>> = query.switchMap {
        drinkRepository.getDrinksByName(Constants.API_TOKEN_KEY, it)
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    fun setFilter(filter: String){
    }
}