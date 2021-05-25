package com.example.drinkbrowserapp.ui.browser

import androidx.lifecycle.ViewModel
import com.example.drinkbrowserapp.network.api.DrinkService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BrowserViewModel @Inject constructor(private val drinkService: DrinkService) : ViewModel() {

//    val retroTest: LiveData<GenericApiResponse<DrinkSearchByNameResponse>> = drinkService.getDrinksByName(
//            "1",
//    "island"
//    )
//    val retroTest2: LiveData<GenericApiResponse<DrinkSearchByIngredientResponse>> = drinkService.getDrinksByIngredientName(
//        "1",
//        "fdhfgjg"
//    )

}