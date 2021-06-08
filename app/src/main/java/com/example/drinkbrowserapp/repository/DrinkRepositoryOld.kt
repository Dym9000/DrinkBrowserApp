package com.example.drinkbrowserapp.repository

import com.example.drinkbrowserapp.network.api.DrinkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkRepositoryOld @Inject constructor(private val retrofitObject: DrinkService) {

//    val mappper = DrinkDtoMapper()
//    fun getDrinksByName(drinkName: String, key: String): LiveData<DataState<List<DrinkDb>>> {
//        return object : NetworkDataStateRepository<SearchByNameDrinkResponse, List<DrinkDb>>() {
//            override fun onFailureResponse(message: String) {
//                result.value = DataState.error(data = null, errorMessage = message)
//            }
//
//            override fun onSuccessResponse(response: ApiSuccessResponse<SearchByNameDrinkResponse>) {
////                Log.d("MainActivity", response.body.drinks.toString())
//                result.value = DataState.success(data = mappper.mapFromList(response.body.drinks))
//            }
//
//            override suspend fun makeRequestCall() =
//                retrofitObject.getDrinksByName(key = key, query = drinkName)
//        }.returnAsLiveData()
//    }
//
//    fun getDrinksByIngredient(
//        ingredientName: String,
//        key: String
//    ): LiveData<DataState<List<FilterSearchRaw>>> {
//        return object :
//            NetworkDataStateRepository<FilterSearchResponse, List<FilterSearchRaw>>() {
//            override fun onFailureResponse(message: String) {
//                result.value = DataState.error(data = null, errorMessage = message)
//            }
//
//            override fun onSuccessResponse(response: ApiSuccessResponse<FilterSearchResponse>) {
//                result.value = DataState.success(data = response.body.drinks)
//            }
//
//            override suspend fun makeRequestCall() =
//                retrofitObject.getDrinksByIngredient(key = key, query = ingredientName)
//        }.returnAsLiveData()
//    }

}