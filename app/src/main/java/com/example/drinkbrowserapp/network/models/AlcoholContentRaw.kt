package com.example.drinkbrowserapp.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AlcoholContentRaw(
    @SerializedName("strAlcoholic")
    @Expose
    var strAlcoholic: String? = null
)
