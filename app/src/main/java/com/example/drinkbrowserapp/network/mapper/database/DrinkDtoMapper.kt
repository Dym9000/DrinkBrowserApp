package com.example.drinkbrowserapp.network.mapper.database

import com.example.drinkbrowserapp.network.dto.DrinkRaw
import com.example.drinkbrowserapp.persistence.entity.DrinkDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkDtoMapper @Inject constructor() : GenericMapper<DrinkRaw, DrinkDb>() {
    override fun mapFrom(input: DrinkRaw): DrinkDb {
        return DrinkDb(
            input.idDrink ?: -1,
            input.name.orEmpty(),
            input.category.orEmpty(),
            input.alcoholContent.orEmpty(),
            input.glass.orEmpty(),
            input.englishInstruction.orEmpty(),
            input.imageUrl.orEmpty(),
            input.ingredient1.orEmpty(),
            input.ingredient2.orEmpty(),
            input.ingredient3.orEmpty(),
            input.ingredient4.orEmpty(),
            input.ingredient5.orEmpty(),
            input.ingredient6.orEmpty(),
            input.ingredient7.orEmpty(),
            input.ingredient8.orEmpty(),
            input.ingredient9.orEmpty(),
            input.ingredient10.orEmpty(),
            input.ingredient11.orEmpty(),
            input.ingredient12.orEmpty(),
            input.ingredient13.orEmpty(),
            input.ingredient14.orEmpty(),
            input.ingredient15.orEmpty(),
            input.measure1.orEmpty(),
            input.measure2.orEmpty(),
            input.measure3.orEmpty(),
            input.measure4.orEmpty(),
            input.measure5.orEmpty(),
            input.measure6.orEmpty(),
            input.measure7.orEmpty(),
            input.measure8.orEmpty(),
            input.measure9.orEmpty(),
            input.measure10.orEmpty(),
            input.measure11.orEmpty(),
            input.measure12.orEmpty(),
            input.measure13.orEmpty(),
            input.measure14.orEmpty(),
            input.measure15.orEmpty()
        )
    }

    override fun mapTo(input: DrinkDb): DrinkRaw {
        return DrinkRaw(
            input.id,
            input.name,
            input.category,
            input.alcoholContent,
            input.glass,
            input.englishInstruction,
            input.imageUrl,
            input.ingredient1,
            input.ingredient2,
            input.ingredient3,
            input.ingredient4,
            input.ingredient5,
            input.ingredient6,
            input.ingredient7,
            input.ingredient8,
            input.ingredient9,
            input.ingredient10,
            input.ingredient11,
            input.ingredient12,
            input.ingredient13,
            input.ingredient14,
            input.ingredient15,
            input.measure1,
            input.measure2,
            input.measure3,
            input.measure4,
            input.measure5,
            input.measure6,
            input.measure7,
            input.measure8,
            input.measure9,
            input.measure10,
            input.measure11,
            input.measure12,
            input.measure13,
            input.measure14,
            input.measure15
        )
    }
}