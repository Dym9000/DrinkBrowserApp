package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.AlcoholContentDomain
import com.example.drinkbrowserapp.persistence.entity.AlcoholContentDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper

class AlcoholContentDbMapper : GenericMapper<AlcoholContentDb, AlcoholContentDomain>() {
    override fun mapFrom(input: AlcoholContentDb): AlcoholContentDomain {
        return AlcoholContentDomain(
            input.alcoholContent
        )
    }

    override fun mapTo(input: AlcoholContentDomain): AlcoholContentDb {
        return AlcoholContentDb(
            input.alcoholContent
        )
    }
}