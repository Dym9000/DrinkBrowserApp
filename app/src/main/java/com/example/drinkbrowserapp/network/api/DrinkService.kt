package com.example.drinkbrowserapp.network.api

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.network.responses.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DrinkService {

    @GET("{key}/search.php")
    fun getDrinksByName(
        @Path("key") key: String,
        @Query("s") query: String
    ): LiveData<GenericApiResponse<SearchByIdOrNameDrinkResponse>>

    @GET("{key}/lookup.php")
    fun getDrinkDetailsById(
        @Path("key") key: String,
        @Query("i") query: String
    ): LiveData<GenericApiResponse<SearchByIdOrNameDrinkResponse>>

    @GET("{key}/filter.php")
    fun getDrinksByIngredient(
        @Path("key") key: String,
        @Query("i") query: String
    ): LiveData<GenericApiResponse<FilterSearchResponse>>

    @GET("{key}/filter.php")
    fun getDrinksByCategory(
        @Path("key") key: String,
        @Query("c") query: String
    ): LiveData<GenericApiResponse<FilterSearchResponse>>

    @GET("{key}/filter.php")
    fun getDrinksByAlcoholContent(
        @Path("key") key: String,
        @Query("a") query: String
    ): LiveData<GenericApiResponse<FilterSearchResponse>>

    @GET("{key}/filter.php")
    fun getDrinksByGlassType(
        @Path("key") key: String,
        @Query("g") query: String
    ): LiveData<GenericApiResponse<FilterSearchResponse>>

    @GET("{key}/list.php")
    fun getCategories(
        @Path("key") key: String,
        @Query("c") query: String
    ): LiveData<GenericApiResponse<CategoryResponse>>

    @GET("{key}/list.php")
    fun getIngredients(
        @Path("key") key: String,
        @Query("i") query: String
    ): LiveData<GenericApiResponse<IngredientResponse>>

    @GET("{key}/list.php")
    fun getAlcoholContents(
        @Path("key") key: String,
        @Query("a") query: String
    ): LiveData<GenericApiResponse<AlcoholContentResponse>>

    @GET("{key}/list.php")
    fun getGlassTypes(
        @Path("key") key: String,
        @Query("g") query: String
    ): LiveData<GenericApiResponse<GlassResponse>>
}
