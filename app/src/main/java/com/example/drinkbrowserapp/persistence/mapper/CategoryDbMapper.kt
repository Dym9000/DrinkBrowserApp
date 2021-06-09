package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.FilterDomainCriteria
import com.example.drinkbrowserapp.persistence.entity.CategoryDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryDbMapper @Inject constructor() :
    GenericMapper<CategoryDb, FilterDomainCriteria>() {
    override fun mapFrom(input: CategoryDb): FilterDomainCriteria {
        return FilterDomainCriteria(input.category)
    }

    override fun mapTo(input: FilterDomainCriteria): CategoryDb {
        return CategoryDb(input.name)
    }
}