package com.example.drinkbrowserapp.network.api

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.network.responses.DrinkSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DrinkService {

    @GET("{key}/search.php??s={query}")
    fun getDrinksByName(
        @Path ("key") key: String,
        @Path ("query") query: String
    ): LiveData<GenericApiResponse<DrinkSearchResponse>>

    @GET("{key}/filter.php?i={query}")
    fun getDrinksByIngredientName(
        @Path ("key") key: String,
        @Path ("query") query: String
    ): LiveData<GenericApiResponse<DrinkSearchResponse>>
}
