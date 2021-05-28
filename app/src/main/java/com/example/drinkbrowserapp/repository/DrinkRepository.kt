package com.example.drinkbrowserapp.repository

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.network.api.ApiSuccessResponse
import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.network.models.DrinkRaw
import com.example.drinkbrowserapp.network.models.FilterSearchRaw
import com.example.drinkbrowserapp.network.responses.FilterSearchResponse
import com.example.drinkbrowserapp.network.responses.SearchByNameDrinkResponse
import com.example.drinkbrowserapp.util.DataState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkRepository @Inject constructor(private val retrofitObject: DrinkService) {

    fun getDrinksByName(drinkName: String, key: String): LiveData<DataState<List<DrinkRaw>>> {
        return object : NetworkBoundResource<SearchByNameDrinkResponse, List<DrinkRaw>>() {
            override fun onFailureResponse(message: String) {
                result.value = DataState.error(data = null, errorMessage = message)
            }

            override fun onSuccessResponse(response: ApiSuccessResponse<SearchByNameDrinkResponse>) {
                result.value = DataState.success(data = response.body.drinks)
            }

            override suspend fun makeRequestCall() =
                retrofitObject.getDrinksByName(key = key, query = drinkName)
        }.returnAsLiveData()
    }

    fun getDrinksByIngredient(
        ingredientName: String,
        key: String
    ): LiveData<DataState<List<FilterSearchRaw>>> {
        return object :
            NetworkBoundResource<FilterSearchResponse, List<FilterSearchRaw>>() {
            override fun onFailureResponse(message: String) {
                result.value = DataState.error(data = null, errorMessage = message)
            }

            override fun onSuccessResponse(response: ApiSuccessResponse<FilterSearchResponse>) {
                result.value = DataState.success(data = response.body.drinks)
            }

            override suspend fun makeRequestCall() =
                retrofitObject.getDrinksByIngredient(key = key, query = ingredientName)
        }.returnAsLiveData()
    }

}