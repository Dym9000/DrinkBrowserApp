package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.models.IngredientRaw
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class IngredientResponse (
    @SerializedName("drinks")
    @Expose
    var ingredients: List<IngredientRaw>
) {
    override fun toString(): String {
        return "DrinkSearchByIngredientResponse(drinks=$ingredients)"
    }
}