package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.IngredientDomain
import com.example.drinkbrowserapp.persistence.entity.IngredientDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class IngredientDbMapper @Inject constructor() : GenericMapper<IngredientDb, IngredientDomain>() {
    override fun mapFrom(input: IngredientDb): IngredientDomain {
        return IngredientDomain(input.ingredient)
    }

    override fun mapTo(input: IngredientDomain): IngredientDb {
        return IngredientDb(input.ingredient)
    }
}