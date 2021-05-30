package com.example.drinkbrowserapp.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IngredientRaw(
    @SerializedName("strIngredient1")
    @Expose
    var strIngredient1: String? = null
)
