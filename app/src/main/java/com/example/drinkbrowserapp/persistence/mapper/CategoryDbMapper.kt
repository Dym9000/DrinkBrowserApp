package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.CategoryDomain
import com.example.drinkbrowserapp.persistence.entity.CategoryDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryDbMapper @Inject constructor() : GenericMapper<CategoryDb, CategoryDomain>() {
    override fun mapFrom(input: CategoryDb): CategoryDomain {
        return CategoryDomain(input.category)
    }

    override fun mapTo(input: CategoryDomain): CategoryDb {
        return CategoryDb(input.category)
    }
}