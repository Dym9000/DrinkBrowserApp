package com.example.drinkbrowserapp.repository

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.network.api.ApiSuccessResponse
import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.network.models.DrinkRaw
import com.example.drinkbrowserapp.network.models.IngredientRaw
import com.example.drinkbrowserapp.network.responses.DrinkSearchByIngredientResponse
import com.example.drinkbrowserapp.network.responses.DrinkSearchByNameResponse
import com.example.drinkbrowserapp.util.DataState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkRepository @Inject constructor(private val retrofitObject: DrinkService) {

    fun getDrinksByName(drinkName: String, key: String): LiveData<DataState<List<DrinkRaw>>> {
        return object : NetworkBoundResource<DrinkSearchByNameResponse, List<DrinkRaw>>() {
            override fun onFailureResponse(message: String) {
                result.value = DataState.error(data = null, errorMessage = message)
            }

            override fun onSuccessResponse(response: ApiSuccessResponse<DrinkSearchByNameResponse>) {
                result.value = DataState.success(data = response.body.drinks)
            }

            override suspend fun makeRequestCall() =
                retrofitObject.getDrinksByName(key = key, query = drinkName)
        }.returnAsLiveData()
    }

    fun getDrinksByIngredient(
        ingredientName: String,
        key: String
    ): LiveData<DataState<List<IngredientRaw>>> {
        return object :
            NetworkBoundResource<DrinkSearchByIngredientResponse, List<IngredientRaw>>() {
            override fun onFailureResponse(message: String) {
                result.value = DataState.error(data = null, errorMessage = message)
            }

            override fun onSuccessResponse(response: ApiSuccessResponse<DrinkSearchByIngredientResponse>) {
                result.value = DataState.success(data = response.body.drinks)
            }

            override suspend fun makeRequestCall() =
                retrofitObject.getDrinksByIngredientName(key = key, query = ingredientName)
        }.returnAsLiveData()
    }

}