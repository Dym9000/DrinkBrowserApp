package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.models.IngredientRaw
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class SearchByIngredientResponse(
    @SerializedName("drinks")
    @Expose
    var drinks: List<IngredientRaw>
)