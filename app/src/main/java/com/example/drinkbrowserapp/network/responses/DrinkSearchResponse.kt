package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.models.GenericNetworkModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DrinkSearchResponse(
    @SerializedName("drinks")
    @Expose
    var drinks: List<GenericNetworkModel>
)
