package com.example.drinkbrowserapp.network.mapper.database

import com.example.drinkbrowserapp.network.dto.AlcoholContentRaw
import com.example.drinkbrowserapp.persistence.entity.AlcoholContentDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper

class AlcoholContentDtoMapper: GenericMapper<AlcoholContentRaw, AlcoholContentDb>() {

    override fun mapFrom(input: AlcoholContentRaw): AlcoholContentDb {
        return AlcoholContentDb(
            input.strAlcoholic.orEmpty()
        )
    }

    override fun mapTo(input: AlcoholContentDb): AlcoholContentRaw {
        return AlcoholContentRaw(input.alcoholContent)
    }
}