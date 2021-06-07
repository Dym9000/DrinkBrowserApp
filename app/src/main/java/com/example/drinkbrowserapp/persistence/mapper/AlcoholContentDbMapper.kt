package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.FilterDomainCriteria
import com.example.drinkbrowserapp.persistence.entity.AlcoholContentDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AlcoholContentDbMapper @Inject constructor() :
    GenericMapper<AlcoholContentDb, FilterDomainCriteria>() {
    override fun mapFrom(input: AlcoholContentDb): FilterDomainCriteria {
        return FilterDomainCriteria(
            input.alcoholContent
        )
    }

    override fun mapTo(input: FilterDomainCriteria): AlcoholContentDb {
        return AlcoholContentDb(
            input.name
        )
    }
}