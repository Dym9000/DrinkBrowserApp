package com.example.drinkbrowserapp.di

import android.content.Context
import androidx.room.Room
import com.example.drinkbrowserapp.persistence.DrinkBrowserDatabase
import com.example.drinkbrowserapp.persistence.dao.DrinksDao
import com.example.drinkbrowserapp.persistence.dao.FilterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DrinksDatabaseModule {

    @Singleton
    @Provides
    fun provideDrinkBrowserDatabase(@ApplicationContext context: Context): DrinkBrowserDatabase {
        return Room.databaseBuilder(
            context,
            DrinkBrowserDatabase::class.java,
            DrinkBrowserDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideDrinksDao(drinkBrowserDatabase: DrinkBrowserDatabase): DrinksDao {
        return drinkBrowserDatabase.drinksDao
    }

    @Singleton
    @Provides
    fun provideFilterDao(drinkBrowserDatabase: DrinkBrowserDatabase): FilterDao {
        return drinkBrowserDatabase.filterDao
    }
}