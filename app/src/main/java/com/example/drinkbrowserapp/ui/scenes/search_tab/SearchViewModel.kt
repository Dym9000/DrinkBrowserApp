package com.example.drinkbrowserapp.ui.scenes.search_tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.drinkbrowserapp.domain.DrinkDomain
import com.example.drinkbrowserapp.ui.common.repository.DrinkRepository
import com.example.drinkbrowserapp.util.Constants
import com.example.drinkbrowserapp.util.DataState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val drinkRepository: DrinkRepository
) : ViewModel() {

    private val _query = MutableLiveData<String>()
    private val query: LiveData<String> = _query

    val searchByNameResult: LiveData<DataState<List<DrinkDomain>>> = query.switchMap {
        drinkRepository.getDrinksByName(Constants.API_TOKEN_KEY, it)
    }

    fun setQuery(query: String) {
        _query.value = query
    }

    /**
     * on item click
     */
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

    fun onSwiped(id: Int){
        drinkRepository.addOrRemoveFromFavList(id)
    }
}