package com.example.drinkbrowserapp.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DrinkRaw(

    @SerializedName("idDrink")
    @Expose
    var idDrink: Int? = null,

    @SerializedName("strDrink")
    @Expose
    val name: String? = null,

    @SerializedName("strCategory")
    @Expose
    val category: String? = null,

    @SerializedName("strAlcoholic")
    @Expose
    val alcoholContent: String? = null,

    @SerializedName("strGlass")
    @Expose
    val glass: String? = null,

    @SerializedName("strInstructions")
    @Expose
    var englishInstruction: String? = null,

    @SerializedName("strDrinkThumb")
    @Expose
    var imageUrl: String? = null,

    @SerializedName("strIngredient1")
    @Expose
    var ingredient1: String? = null,

    @SerializedName("strIngredient2")
    @Expose
    var ingredient2: String? = null,

    @SerializedName("strIngredient3")
    @Expose
    var ingredient3: String? = null,

    @SerializedName("strIngredient4")
    @Expose
    var ingredient4: String? = null,

    @SerializedName("strIngredient5")
    @Expose
    var ingredient5: String? = null,

    @SerializedName("strIngredient6")
    @Expose
    var ingredient6: String? = null,

    @SerializedName("strIngredient7")
    @Expose
    var ingredient7: String? = null,

    @SerializedName("strIngredient8")
    @Expose
    var ingredient8: String? = null,

    @SerializedName("strIngredient9")
    @Expose
    var ingredient9: String? = null,

    @SerializedName("strIngredient10")
    @Expose
    var ingredient10: String? = null,

    @SerializedName("strIngredient11")
    @Expose
    var ingredient11: String? = null,

    @SerializedName("strIngredient12")
    @Expose
    var ingredient12: String? = null,

    @SerializedName("strIngredient13")
    @Expose
    var ingredient13: String? = null,

    @SerializedName("strIngredient14")
    @Expose
    var ingredient14: String? = null,

    @SerializedName("strIngredient15")
    @Expose
    var ingredient15: String? = null,

    @SerializedName("strMeasure1")
    @Expose
    var measure1: String? = null,

    @SerializedName("strMeasure2")
    @Expose
    var measure2: String? = null,

    @SerializedName("strMeasure3")
    @Expose
    var measure3: String? = null,

    @SerializedName("strMeasure4")
    @Expose
    var measure4: String? = null,

    @SerializedName("strMeasure5")
    @Expose
    var measure5: String? = null,

    @SerializedName("strMeasure6")
    @Expose
    var measure6: String? = null,

    @SerializedName("strMeasure7")
    @Expose
    var measure7: String? = null,

    @SerializedName("strMeasure8")
    @Expose
    var measure8: String? = null,

    @SerializedName("strMeasure9")
    @Expose
    var measure9: String? = null,

    @SerializedName("strMeasure10")
    @Expose
    var measure10: String? = null,

    @SerializedName("strMeasure11")
    @Expose
    var measure11: String? = null,

    @SerializedName("strMeasure12")
    @Expose
    var measure12: String? = null,

    @SerializedName("strMeasure13")
    @Expose
    var measure13: String? = null,

    @SerializedName("strMeasure14")
    @Expose
    var measure14: String? = null,

    @SerializedName("strMeasure15")
    @Expose
    var measure15: String? = null

)