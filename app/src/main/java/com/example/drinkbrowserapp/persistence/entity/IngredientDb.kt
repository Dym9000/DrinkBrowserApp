package com.example.drinkbrowserapp.persistence.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ingredients")
data class IngredientDb(
    @PrimaryKey
    @NonNull
    val ingredient: String
)
