package com.example.drinkbrowserapp.network.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class IngredientRaw(

    @SerializedName("strDrink")
    @Expose
    var strDrink: String? = null,

    @SerializedName("strDrinkThumb")
    @Expose
    var strDrinkThumb: String? = null,

    @SerializedName("idDrink")
    @Expose
    var idDrink: String? = null

) : GenericNetworkModel
