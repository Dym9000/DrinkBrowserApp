package com.example.drinkbrowserapp.persistence.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinks_by_filters")
data class FilterSearchDb(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "drinkId")
    val drinkId: Int,

    @ColumnInfo(name = "drinkName")
    @NonNull
    val drinkName: String,

    @ColumnInfo(name = "imageUrl")
    val imageUrl: String
)
