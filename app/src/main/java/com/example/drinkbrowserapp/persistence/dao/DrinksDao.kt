package com.example.drinkbrowserapp.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.drinkbrowserapp.persistence.entity.DrinkDb
import com.example.drinkbrowserapp.persistence.entity.FilterSearchDb

/**
 *      DAO for working with drinks' search results
 */
@Dao
interface DrinksDao {

    /**
     *      FilterSearchDb
     */
    @Insert
    suspend fun saveFilterSearchResult(drinks: List<FilterSearchDb>)

    @Query("Delete from drinks_by_filters")
    suspend fun clearDrinksByFilter()

    @Query("Select * from drinks_by_filters")
    fun getAllDrinksByFilter(): LiveData<List<FilterSearchDb>>

    /**
     *      DrinkDB Entity
     */
    @Insert
    suspend fun saveDrinksByNameResult(drinks: List<DrinkDb>)

    @Query("Delete from drinks")
    suspend fun clearDrinksByName()

    @Query("Select * from drinks")
    fun getAllDrinksByName(): LiveData<List<DrinkDb>>

    /**
     *      Common query for a drink's details
     */
    @Query("Select * from drinks where id = :id")
    fun getDrinkDetails(id: Int): LiveData<DrinkDb>

}