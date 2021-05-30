package com.example.drinkbrowserapp.network.dto

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DrinkRaw(

    @SerializedName("idDrink")
    @Expose
    var idDrink: Int? = null,

    @SerializedName("name")
    @Expose
    val name: String? = null,

    @SerializedName("category")
    @Expose
    val category: String? = null,

    @SerializedName("alcoholContent")
    @Expose
    val alcoholContent: String? = null,

    @SerializedName("glass")
    @Expose
    val glass: String? = null,

    @SerializedName("englishInstruction")
    @Expose
    var englishInstruction: String? = null,

    @SerializedName("imageUrl")
    @Expose
    var imageUrl: String? = null,

    @SerializedName("ingredient1")
    @Expose
    var ingredient1: String? = null,

    @SerializedName("ingredient2")
    @Expose
    var ingredient2: String? = null,

    @SerializedName("ingredient3")
    @Expose
    var ingredient3: String? = null,

    @SerializedName("ingredient4")
    @Expose
    var ingredient4: String? = null,

    @SerializedName("ingredient5")
    @Expose
    var ingredient5: String? = null,

    @SerializedName("ingredient6")
    @Expose
    var ingredient6: String? = null,

    @SerializedName("ingredient7")
    @Expose
    var ingredient7: String? = null,

    @SerializedName("ingredient8")
    @Expose
    var ingredient8: String? = null,

    @SerializedName("ingredient9")
    @Expose
    var ingredient9: String? = null,

    @SerializedName("ingredient10")
    @Expose
    var ingredient10: String? = null,

    @SerializedName("ingredient11")
    @Expose
    var ingredient11: String? = null,

    @SerializedName("ingredient12")
    @Expose
    var ingredient12: String? = null,

    @SerializedName("ingredient13")
    @Expose
    var ingredient13: String? = null,

    @SerializedName("ingredient14")
    @Expose
    var ingredient14: String? = null,

    @SerializedName("ingredient15")
    @Expose
    var ingredient15: String? = null,

    @SerializedName("measure1")
    @Expose
    var measure1: String? = null,

    @SerializedName("measure2")
    @Expose
    var measure2: String? = null,

    @SerializedName("measure3")
    @Expose
    var measure3: String? = null,

    @SerializedName("measure4")
    @Expose
    var measure4: String? = null,

    @SerializedName("measure5")
    @Expose
    var measure5: String? = null,

    @SerializedName("measure6")
    @Expose
    var measure6: String? = null,

    @SerializedName("measure7")
    @Expose
    var measure7: String? = null,

    @SerializedName("measure8")
    @Expose
    var measure8: String? = null,

    @SerializedName("measure9")
    @Expose
    var measure9: String? = null,

    @SerializedName("measure10")
    @Expose
    var measure10: String? = null,

    @SerializedName("measure11")
    @Expose
    var measure11: String? = null,

    @SerializedName("measure12")
    @Expose
    var measure12: String? = null,

    @SerializedName("measure13")
    @Expose
    var measure13: String? = null,

    @SerializedName("measure14")
    @Expose
    var measure14: String? = null,

    @SerializedName("measure15")
    @Expose
    var measure15: String? = null

)