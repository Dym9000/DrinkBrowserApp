package com.example.drinkbrowserapp.persistence.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinks")
data class DrinkDb(
    @PrimaryKey
    @NonNull
    val id: Int,

    @ColumnInfo(name = "drinkName")
    @NonNull
    val name: String,

    @ColumnInfo(name = "category")
    @NonNull
    val category: String,

    @ColumnInfo(name = "alcoholContent")
    @NonNull
    val alcoholContent: String,

    @ColumnInfo(name = "glass")
    @NonNull
    val glass: String,

    @ColumnInfo(name = "englishInstruction")
    var englishInstruction: String,

    @ColumnInfo(name = "imageUrl")
    var imageUrl: String,

    @ColumnInfo(name = "ingredient1")
    var ingredient1: String,

    @ColumnInfo(name = "ingredient2")
    var ingredient2: String,

    @ColumnInfo(name = "ingredient3")
    var ingredient3: String,

    @ColumnInfo(name = "ingredient4")
    var ingredient4: String,

    @ColumnInfo(name = "ingredient5")
    var ingredient5: String,

    @ColumnInfo(name = "ingredient6")
    var ingredient6: String,

    @ColumnInfo(name = "ingredient7")
    var ingredient7: String,

    @ColumnInfo(name = "ingredient8")
    var ingredient8: String,

    @ColumnInfo(name = "ingredient9")
    var ingredient9: String,

    @ColumnInfo(name = "ingredient10")
    var ingredient10: String,

    @ColumnInfo(name = "ingredient11")
    var ingredient11: String,

    @ColumnInfo(name = "ingredient12")
    var ingredient12: String,

    @ColumnInfo(name = "ingredient13")
    var ingredient13: String,

    @ColumnInfo(name = "ingredient14")
    var ingredient14: String,

    @ColumnInfo(name = "ingredient15")
    var ingredient15: String,

    @ColumnInfo(name = "measure1")
    var measure1: String,

    @ColumnInfo(name = "measure2")
    var measure2: String,

    @ColumnInfo(name = "measure3")
    var measure3: String,

    @ColumnInfo(name = "measure4")
    var measure4: String,

    @ColumnInfo(name = "measure5")
    var measure5: String,

    @ColumnInfo(name = "measure6")
    var measure6: String,

    @ColumnInfo(name = "measure7")
    var measure7: String,

    @ColumnInfo(name = "measure8")
    var measure8: String,

    @ColumnInfo(name = "measure9")
    var measure9: String,

    @ColumnInfo(name = "measure10")
    var measure10: String,

    @ColumnInfo(name = "measure11")
    var measure11: String,

    @ColumnInfo(name = "measure12")
    var measure12: String,

    @ColumnInfo(name = "measure13")
    var measure13: String,

    @ColumnInfo(name = "measure14")
    var measure14: String,

    @ColumnInfo(name = "measure15")
    var measure15: String,

    @ColumnInfo(name = "isFavourite")
    var isFavourite: Int = 0,

    @ColumnInfo(name = "isSearchResult")
    var isSearchResult: Int = 1,

    @ColumnInfo(name = "isDrinkDetailsScene")
    var isDrinkDetailsScene: Int = 0
)
