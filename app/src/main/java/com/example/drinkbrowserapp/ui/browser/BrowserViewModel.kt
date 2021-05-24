package com.example.drinkbrowserapp.ui.browser

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.network.api.GenericApiResponse
import com.example.drinkbrowserapp.network.responses.DrinkSearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrowserViewModel @Inject constructor(private val drinkService: DrinkService):ViewModel() {

    val retroTest: LiveData<GenericApiResponse<DrinkSearchResponse>>

    init{
        retroTest = drinkService.getDrinksByName(
                "1",
        "island"
        )
    }
}