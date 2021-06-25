package com.example.drinkbrowserapp.ui.scenes.favourites_tab

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.ui.common.repository.DrinkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(private val drinkRepository: DrinkRepository): ViewModel() {

    val favouriteList = drinkRepository.getFavouriteDrinks()

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