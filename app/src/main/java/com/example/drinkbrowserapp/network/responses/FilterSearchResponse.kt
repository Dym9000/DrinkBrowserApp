package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.dto.FilterSearchRaw
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class FilterSearchResponse(
    @SerializedName("drinks")
    @Expose
    var drinks: List<FilterSearchRaw>
) {
    override fun toString(): String {
        return "DrinkSearchByIngredientResponse(drinks=$drinks)"
    }
}

