package com.example.drinkbrowserapp.ui.scenes.common.drink_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.domain.DrinkDomain
import com.example.drinkbrowserapp.util.Constants

class DrinkDetailsViewModel(drinkId: Int, drinkRepository: DrinkRepository) : ViewModel() {

    val drinkDetailsList = drinkRepository.getDrinkDetails(Constants.API_TOKEN_KEY, drinkId)

    private val _drinkDetails = MutableLiveData<DrinkDomain>()
    val drinkDetails: LiveData<DrinkDomain>
        get() {
            return _drinkDetails
        }

    fun onDataFetched(data: DrinkDomain) {
        _drinkDetails.value = data
    }

}