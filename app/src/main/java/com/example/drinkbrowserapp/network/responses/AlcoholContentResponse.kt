package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.models.AlcoholContentRaw
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class AlcoholContentResponse(
    @SerializedName("drinks")
    @Expose
    var alcoholContents: List<AlcoholContentRaw>
) {
    override fun toString(): String {
        return "DrinkSearchByIngredientResponse(drinks=$alcoholContents)"
    }
}