package com.example.drinkbrowserapp.network.api

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.network.responses.DrinkSearchByIngredientResponse
import com.example.drinkbrowserapp.network.responses.DrinkSearchByNameResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DrinkService {

    @GET("{key}/search.php")
    fun getDrinksByName(
        @Path("key") key: String,
        @Query("s") query: String
    ): LiveData<GenericApiResponse<DrinkSearchByNameResponse>>

    @GET("{key}/filter.php")
    fun getDrinksByIngredientName(
        @Path("key") key: String,
        @Query("i") query: String
    ): LiveData<GenericApiResponse<DrinkSearchByIngredientResponse>>

}
