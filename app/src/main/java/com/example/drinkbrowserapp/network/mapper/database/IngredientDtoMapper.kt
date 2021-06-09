package com.example.drinkbrowserapp.network.mapper.database

import com.example.drinkbrowserapp.network.dto.IngredientRaw
import com.example.drinkbrowserapp.persistence.entity.IngredientDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IngredientDtoMapper @Inject constructor() : GenericMapper<IngredientRaw, IngredientDb>() {
    override fun mapFrom(input: IngredientRaw): IngredientDb {
        return IngredientDb(input.strIngredient1.orEmpty())
    }

    override fun mapTo(input: IngredientDb): IngredientRaw {
        return IngredientRaw(input.ingredient)
    }
}