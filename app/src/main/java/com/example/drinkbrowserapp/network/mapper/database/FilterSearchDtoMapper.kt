package com.example.drinkbrowserapp.network.mapper.database

import com.example.drinkbrowserapp.network.dto.FilterSearchRaw
import com.example.drinkbrowserapp.persistence.entity.FilterSearchDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper

class FilterSearchDtoMapper : GenericMapper<FilterSearchRaw, FilterSearchDb>() {
    override fun mapFrom(input: FilterSearchRaw): FilterSearchDb {
        return FilterSearchDb(
            drinkId = input.idDrink ?: -1,
            drinkName = input.strDrink.orEmpty(),
            imageUrl = input.strDrinkThumb.orEmpty()
        )
    }

    override fun mapTo(input: FilterSearchDb): FilterSearchRaw {
        return FilterSearchRaw(
            strDrink = input.drinkName,
            strDrinkThumb = input.imageUrl,
            idDrink = input.drinkId
        )
    }
}