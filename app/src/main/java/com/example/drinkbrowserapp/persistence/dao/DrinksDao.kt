package com.example.drinkbrowserapp.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
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
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveDrinksByNameResult(drinks: List<DrinkDb>)

    //  DRINK DETAILS SCENE
    @Query("Select * from drinks where id = :id")
    fun getDrinkDetails(id: Int): LiveData<List<DrinkDb>>

    @Query("UPDATE drinks SET isDrinkDetailsScene = 1 , isSearchResult = -1 where id = :id")
    suspend fun setDrinkAsDrinkDetail(id: Int)

    @Query("Delete from drinks where isDrinkDetailsScene = 1 and isSearchResult = -1 and id = :id")
    suspend fun clearDrinkDetails(id: Int)

    @Transaction
    suspend fun saveDrinkDetails(drinks: List<DrinkDb>, id: Int){
        saveDrinksByNameResult(drinks)
        setDrinkAsDrinkDetail(id)
    }

    //  SEARCH SCENE
    @Query("Delete from drinks where isSearchResult = 1")
    suspend fun clearDrinksByName()

    @Query("Select * from drinks where isSearchResult = 1")
    fun getAllDrinksByName(): LiveData<List<DrinkDb>>

    @Query("SELECT EXISTS(SELECT 1 FROM drinks WHERE id = :id)")
    suspend fun isDrinkInDatabase(id: Int): Int

    //  FAVORITE SCENE
    @Query("SELECT * from drinks where isFavourite = 1")
    fun getFavoriteDrinks(): LiveData<List<DrinkDb>>

}