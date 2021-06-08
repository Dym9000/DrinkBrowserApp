package com.example.drinkbrowserapp.ui.scenes.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.domain.DrinkDomain
import com.example.drinkbrowserapp.util.Constants

class DrinkDetailsViewModel(drinkId: Int, drinkRepository: DrinkRepository)
    : ViewModel() {

        val drinkDetailsList = drinkRepository.getDrinkDetails(Constants.API_TOKEN_KEY, drinkId)

    val drinkDetails: LiveData<DrinkDomain> = Transformations.map(drinkDetailsList){
        it.data?.get(0)
    }

}