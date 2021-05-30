package com.example.drinkbrowserapp.persistence.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryDb(
    @PrimaryKey
    @NonNull
    val category: String
)
