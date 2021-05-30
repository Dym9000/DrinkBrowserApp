package com.example.drinkbrowserapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.network.api.ApiSuccessResponse
import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.network.dto.FilterSearchRaw
import com.example.drinkbrowserapp.network.mapper.database.DrinkDtoMapper
import com.example.drinkbrowserapp.network.responses.FilterSearchResponse
import com.example.drinkbrowserapp.network.responses.SearchByNameDrinkResponse
import com.example.drinkbrowserapp.persistence.entity.DrinkDb
import com.example.drinkbrowserapp.util.DataState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkRepository @Inject constructor(private val retrofitObject: DrinkService) {

    val mappper = DrinkDtoMapper()
    fun getDrinksByName(drinkName: String, key: String): LiveData<DataState<List<DrinkDb>>> {
        return object : NetworkBoundResource<SearchByNameDrinkResponse, List<DrinkDb>>() {
            override fun onFailureResponse(message: String) {
                result.value = DataState.error(data = null, errorMessage = message)
            }

            override fun onSuccessResponse(response: ApiSuccessResponse<SearchByNameDrinkResponse>) {
                Log.d("MainActivity", response.body.drinks.toString())
                result.value = DataState.success(data = mappper.mapFromList(response.body.drinks))
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