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

    @Query("UPDATE drinks SET isDrinkDetailsScene = 1 , isSearchResult = 0 where id = :id")
    suspend fun setDrinkAsDrinkDetail(id: Int)

    @Query("Delete from drinks where isDrinkDetailsScene = 1 and isFavourite = 0 and isSearchResult = 0 and id = :id")
    suspend fun clearDrinkDetails(id: Int)

    @Transaction
    suspend fun saveDrinkDetails(drinks: List<DrinkDb>, id: Int) {
        saveDrinksByNameResult(drinks)
        setDrinkAsDrinkDetail(id)
    }


    //  SEARCH SCENE
    @Query("Delete from drinks where isSearchResult = 1 and isFavourite = 0 and isDrinkDetailsScene = 0")
    suspend fun clearDrinksByName()

    @Query("Select * from drinks where isSearchResult = 1 and isFavourite = 0 ORDER by drinkName")
    fun getAllDrinksByName(): LiveData<List<DrinkDb>>

    @Query("SELECT EXISTS(SELECT 1 FROM drinks WHERE id = :id)")
    suspend fun isDrinkInDatabase(id: Int): Int


    //  FAVORITE SCENE
    @Query("SELECT * from drinks where isFavourite = 1 ORDER by drinkName")
    fun getFavoriteDrinks(): LiveData<List<DrinkDb>>

    @Query("SELECT EXISTS (SELECT 1 from drinks where id = :id and isFavourite = 1)")
    suspend fun isInFavourites(id: Int): Int

    @Query("UPDATE drinks SET isFavourite = 1 , isDrinkDetailsScene = 0 where id = :id")
    suspend fun addToFavourites(id: Int)

    @Query("DELETE from drinks where id = :id")
    suspend fun removeFromFavourites(id: Int)


}