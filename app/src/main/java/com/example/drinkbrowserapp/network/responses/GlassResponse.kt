package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.dto.GlassRaw
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GlassResponse(
    @SerializedName("drinks")
    @Expose
    var glasses: List<GlassRaw>
) {
    override fun toString(): String {
        return "DrinkSearchByIngredientResponse(drinks=$glasses)"
    }
}