package com.example.drinkbrowserapp.network.api

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.network.responses.FilterSearchResponse
import com.example.drinkbrowserapp.network.responses.SearchByNameDrinkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DrinkService {

    @GET("{key}/search.php")
    fun getDrinksByName(
        @Path("key") key: String,
        @Query("s") query: String
    ): LiveData<GenericApiResponse<SearchByNameDrinkResponse>>

    @GET("{key}/filter.php")
    fun getDrinksByIngredientName(
        @Path("key") key: String,
        @Query("i") query: String
    ): LiveData<GenericApiResponse<FilterSearchResponse>>

}
