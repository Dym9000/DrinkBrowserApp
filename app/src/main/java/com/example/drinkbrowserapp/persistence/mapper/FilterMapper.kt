package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.FilterDomainCriteria
import com.example.drinkbrowserapp.persistence.entity.IngredientDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilterMapper @Inject constructor() :
    GenericMapper<IngredientDb, FilterDomainCriteria>() {
    override fun mapFrom(input: IngredientDb): FilterDomainCriteria {
        return FilterDomainCriteria(input.ingredient)
    }

    override fun mapTo(input: FilterDomainCriteria): IngredientDb {
        return IngredientDb(input.name)
    }

}