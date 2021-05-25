package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.models.IngredientRaw
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DrinkSearchByIngredientResponse(
    @SerializedName("drinks")
    @Expose
    var drinks: List<IngredientRaw>
) {
    override fun toString(): String {
        return "DrinkSearchByIngredientResponse(drinks=$drinks)"
    }
}

