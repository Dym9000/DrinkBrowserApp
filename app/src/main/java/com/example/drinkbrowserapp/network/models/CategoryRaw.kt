package com.example.drinkbrowserapp.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryRaw(
    @SerializedName("strCategory")
    @Expose
    var strCategory: String? = null
)
