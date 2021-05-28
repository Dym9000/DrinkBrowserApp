package com.example.drinkbrowserapp.network.responses

import com.example.drinkbrowserapp.network.models.CategoryRaw
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CategoryResponse(
    @SerializedName("drinks")
    @Expose
    var categories: List<CategoryRaw>
    ){
        override fun toString(): String {
            return "DrinkSearchByIngredientResponse(drinks=$categories)"
        }
}