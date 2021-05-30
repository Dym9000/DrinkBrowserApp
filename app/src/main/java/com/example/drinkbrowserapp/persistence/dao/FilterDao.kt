package com.example.drinkbrowserapp.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.drinkbrowserapp.persistence.entity.AlcoholContentDb
import com.example.drinkbrowserapp.persistence.entity.CategoryDb
import com.example.drinkbrowserapp.persistence.entity.GlassDb
import com.example.drinkbrowserapp.persistence.entity.IngredientDb


/**
 *      DAO for working with filters
 */
@Dao
interface FilterDao {

    /**
     *      CategoryDb
     */
    @Insert
    suspend fun saveCategories(categories: List<CategoryDb>)

    @Query("Delete from categories")
    suspend fun clearCategories()

    @Query("Select * from categories")
    fun getCategories(): LiveData<List<CategoryDb>>

    /**
     *      IngredientDb
     */
    @Insert
    suspend fun saveIngredients(ingredients: List<IngredientDb>)

    @Query("Delete from ingredients")
    suspend fun clearIngredients()

    @Query("Select * from ingredients")
    fun getIngredients(): LiveData<List<IngredientDb>>

    /**
     *      GlassDb
     */
    @Insert
    suspend fun saveGlasses(glasses: List<GlassDb>)

    @Query("Delete from glasses")
    suspend fun clearGlasses()

    @Query("Select * from glasses")
    fun getGlasses(): LiveData<List<GlassDb>>

    /**
     *      AlcoholContentDb
     */
    @Insert
    suspend fun saveAlcoholContents(alcoholContents: List<AlcoholContentDb>)

    @Query("Delete from alcohol_contents")
    suspend fun clearAlcoholContents()

    @Query("Select * from alcohol_contents")
    fun getAlcoholContents(): LiveData<List<AlcoholContentDb>>
}