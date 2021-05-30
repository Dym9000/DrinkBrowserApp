package com.example.drinkbrowserapp.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FilterSearchRaw(

    @SerializedName("strDrink")
    @Expose
    var strDrink: String? = null,

    @SerializedName("strDrinkThumb")
    @Expose
    var strDrinkThumb: String? = null,

    @SerializedName("idDrink")
    @Expose
    var idDrink: Int? = null

)
