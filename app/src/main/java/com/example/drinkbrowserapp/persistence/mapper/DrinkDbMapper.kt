package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.DrinkDomain
import com.example.drinkbrowserapp.persistence.entity.DrinkDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper

class DrinkDbMapper: GenericMapper<DrinkDb, DrinkDomain>(){

    override fun mapFrom(input: DrinkDb): DrinkDomain {
        val ingredients = mapIngredientsFrom(input)
        val measures = mapMeasuresFrom(input)
        return DrinkDomain(
            input.id,
            input.name,
            input.category,
            input.alcoholContent,
            input.glass,
            input.englishInstruction,
            input.imageUrl,
            ingredients,
            measures
        )
    }

    override fun mapTo(input: DrinkDomain): DrinkDb {
        return DrinkDb(
            input.id,
            input.name,
            input.category,
            input.alcoholContent,
            input.glass,
            input.englishInstruction,
            input.imageUrl,
            input.ingredients[0]?.ingredient?.orEmpty(),
            input.ingredients[1]?.ingredient?.orEmpty(),
            input.ingredients[2]?.ingredient?.orEmpty(),
            input.ingredients[3]?.ingredient?.orEmpty(),
            input.ingredients[4]?.ingredient?.orEmpty(),
            input.ingredients[5]?.ingredient?.orEmpty(),
            input.ingredients[6]?.ingredient?.orEmpty(),
            input.ingredients[7]?.ingredient?.orEmpty(),
            input.ingredients[8]?.ingredient?.orEmpty(),
            input.ingredients[9]?.ingredient?.orEmpty(),
            input.ingredients[10]?.ingredient?.orEmpty(),
            input.ingredients[11]?.ingredient?.orEmpty(),
            input.ingredients[12]?.ingredient?.orEmpty(),
            input.ingredients[13]?.ingredient?.orEmpty(),
            input.ingredients[14]?.ingredient?.orEmpty(),
            input.measures[0]?.measure?.orEmpty(),
            input.measures[1]?.measure?.orEmpty(),
            input.measures[2]?.measure?.orEmpty(),
            input.measures[3]?.measure?.orEmpty(),
            input.measures[4]?.measure?.orEmpty(),
            input.measures[5]?.measure?.orEmpty(),
            input.measures[6]?.measure?.orEmpty(),
            input.measures[7]?.measure?.orEmpty(),
            input.measures[8]?.measure?.orEmpty(),
            input.measures[9]?.measure?.orEmpty(),
            input.measures[10]?.measure?.orEmpty(),
            input.measures[11]?.measure?.orEmpty(),
            input.measures[12]?.measure?.orEmpty(),
            input.measures[13]?.measure?.orEmpty(),
            input.measures[14]?.measure?.orEmpty()
            )
    }

    private fun mapIngredientsFrom(input: DrinkDb): List<DrinkDomain.Ingredient> {
        return mutableListOf(
            DrinkDomain.Ingredient(input.ingredient1),
            DrinkDomain.Ingredient(input.ingredient2),
            DrinkDomain.Ingredient(input.ingredient3),
            DrinkDomain.Ingredient(input.ingredient4),
            DrinkDomain.Ingredient(input.ingredient5),
            DrinkDomain.Ingredient(input.ingredient6),
            DrinkDomain.Ingredient(input.ingredient7),
            DrinkDomain.Ingredient(input.ingredient8),
            DrinkDomain.Ingredient(input.ingredient9),
            DrinkDomain.Ingredient(input.ingredient10),
            DrinkDomain.Ingredient(input.ingredient11),
            DrinkDomain.Ingredient(input.ingredient12),
            DrinkDomain.Ingredient(input.ingredient13),
            DrinkDomain.Ingredient(input.ingredient14),
            DrinkDomain.Ingredient(input.ingredient15)
        ).filter {
            it.ingredient.isNotEmpty()
        }
    }

    private fun mapMeasuresFrom(input: DrinkDb): List<DrinkDomain.Measure>{
        return mutableListOf(
            DrinkDomain.Measure(input.measure1),
            DrinkDomain.Measure(input.measure2),
            DrinkDomain.Measure(input.measure3),
            DrinkDomain.Measure(input.measure4),
            DrinkDomain.Measure(input.measure5),
            DrinkDomain.Measure(input.measure6),
            DrinkDomain.Measure(input.measure7),
            DrinkDomain.Measure(input.measure8),
            DrinkDomain.Measure(input.measure9),
            DrinkDomain.Measure(input.measure10),
            DrinkDomain.Measure(input.measure11),
            DrinkDomain.Measure(input.measure12),
            DrinkDomain.Measure(input.measure13),
            DrinkDomain.Measure(input.measure14),
            DrinkDomain.Measure(input.measure15)
        ).filter {
            it.measure.isNotEmpty()
        }
    }
}