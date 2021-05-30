package com.example.drinkbrowserapp.persistence.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alcohol_contents")
data class AlcoholContentDb(
    @PrimaryKey
    @NonNull
    val alcoholContent: String
)
