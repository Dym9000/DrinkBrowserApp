package com.example.drinkbrowserapp.persistence.entity

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "glasses")
data class GlassDb(
    @PrimaryKey
    @NonNull
    val glassType: String
)
