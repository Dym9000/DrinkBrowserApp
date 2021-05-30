package com.example.drinkbrowserapp.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CategoryRaw(
    @SerializedName("strCategory")
    @Expose
    var strCategory: String? = null
)
