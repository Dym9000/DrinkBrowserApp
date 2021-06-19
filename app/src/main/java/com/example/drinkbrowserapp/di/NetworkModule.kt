package com.example.drinkbrowserapp.di

import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.network.util.LiveDataCallAdapterFactory
import com.example.drinkbrowserapp.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideDrinkService(): DrinkService {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()
            .create(DrinkService::class.java)
    }
}