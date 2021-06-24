package com.example.drinkbrowserapp.ui.scenes.common.drink_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.drinkbrowserapp.ui.common.repository.DrinkRepository

class DrinkDetailsViewModelFactory(
    private val drinkId: Int,
    private val drinkRepository: DrinkRepository
) :
    ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DrinkDetailsViewModel::class.java)) {
            return DrinkDetailsViewModel(drinkId, drinkRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}