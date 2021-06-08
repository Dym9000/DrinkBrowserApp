package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.dto.DrinkRaw
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchByIdOrNameDrinkResponse(
    @SerializedName("drinks")
    @Expose
    var drinks: List<DrinkRaw>
)
