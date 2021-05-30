package com.example.drinkbrowserapp.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class GlassRaw(
    @SerializedName("strGlass")
    @Expose
    var strGlass: String? = null
)
