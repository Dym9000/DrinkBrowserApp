package com.example.drinkbrowserapp.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.drinkbrowserapp.persistence.dao.DrinksDao
import com.example.drinkbrowserapp.persistence.dao.FilterDao
import com.example.drinkbrowserapp.persistence.entity.*

@Database(
    entities = [
        AlcoholContentDb::class,
        CategoryDb::class,
        GlassDb::class,
        IngredientDb::class,
        FilterSearchDb::class,
        DrinkDb::class,
    ], version = 2, exportSchema = false
)
abstract class DrinkBrowserDatabase : RoomDatabase() {
    abstract val drinksDao: DrinksDao
    abstract val filterDao: FilterDao

    companion object {
        val DATABASE_NAME: String = "drinks_database"
    }
}