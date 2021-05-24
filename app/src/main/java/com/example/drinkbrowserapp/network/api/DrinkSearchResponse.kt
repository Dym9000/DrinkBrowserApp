package com.example.drinkbrowserapp.network.api

import com.example.drinkbrowserapp.network.model.NetworkModel
import com.google.gson.annotations.SerializedName

data class DrinkSearchResponse(
    @SerializedName("drinks")
    var drinks: List<NetworkModel>
)
