package com.example.drinkbrowserapp.domain

data class DrinkDomain(
    val id: Int,
    val name: String,
    val category: String,
    val alcoholContent: String,
    val glass: String,
    var englishInstruction: String,
    var imageUrl: String,
    var ingredients: List<Ingredient>,
    var measures: List<Measure>
) {
    data class Ingredient(
        val ingredient: String
    ) {
        override fun toString(): String {
            return ingredient
        }
    }

    data class Measure(
        val measure: String
    ) {
        override fun toString(): String {
            return measure
        }
    }
}