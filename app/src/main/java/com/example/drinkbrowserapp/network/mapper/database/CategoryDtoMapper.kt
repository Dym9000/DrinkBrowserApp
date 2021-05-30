package com.example.drinkbrowserapp.network.mapper.database

import com.example.drinkbrowserapp.network.dto.CategoryRaw
import com.example.drinkbrowserapp.persistence.entity.CategoryDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper

class CategoryDtoMapper : GenericMapper<CategoryRaw, CategoryDb>() {

    override fun mapFrom(input: CategoryRaw): CategoryDb {
        return CategoryDb(
            input.strCategory.orEmpty()
        )
    }

    override fun mapTo(input: CategoryDb): CategoryRaw {
        return CategoryRaw(
            input.category
        )
    }
}