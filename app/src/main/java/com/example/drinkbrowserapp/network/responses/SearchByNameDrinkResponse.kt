package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.models.DrinkRaw
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchByNameDrinkResponse(
    @SerializedName("drinks")
    @Expose
    var drinks: List<DrinkRaw>
)
