package com.example.drinkbrowserapp.network.mapper.database

import com.example.drinkbrowserapp.network.dto.AlcoholContentRaw
import com.example.drinkbrowserapp.persistence.entity.AlcoholContentDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper

class AlcoholContent: GenericMapper<AlcoholContentRaw, AlcoholContentDb> {

    override fun mapFromList(input: List<AlcoholContentRaw>): List<AlcoholContentDb> {
        return input.map{
            mapFrom(it)
        }
    }

    override fun mapFrom(input: AlcoholContentRaw): AlcoholContentDb {
        return AlcoholContentDb(
            input.strAlcoholic.orEmpty()
        )
    }

    override fun mapToList(input: List<AlcoholContentDb>): List<AlcoholContentRaw> {
        return input.map{
            mapTo(it)
        }
    }

    override fun mapTo(input: AlcoholContentDb): AlcoholContentRaw {
        return AlcoholContentRaw(input.alcoholContent)
    }
}