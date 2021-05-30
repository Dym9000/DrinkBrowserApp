package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.IngredientDomain
import com.example.drinkbrowserapp.persistence.entity.IngredientDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper

class IngredientDbMapper: GenericMapper<IngredientDb, IngredientDomain>(){
    override fun mapFrom(input: IngredientDb): IngredientDomain {
        return IngredientDomain(input.ingredient)
    }

    override fun mapTo(input: IngredientDomain): IngredientDb {
        return IngredientDb(input.ingredient)
    }
}