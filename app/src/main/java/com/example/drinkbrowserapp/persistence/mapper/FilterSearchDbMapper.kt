package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.FilterSearchDomain
import com.example.drinkbrowserapp.persistence.entity.FilterSearchDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilterSearchDbMapper @Inject constructor() :
    GenericMapper<FilterSearchDb, FilterSearchDomain>() {
    override fun mapFrom(input: FilterSearchDb): FilterSearchDomain {
        return FilterSearchDomain(input.drinkId, input.drinkName, input.imageUrl)
    }

    override fun mapTo(input: FilterSearchDomain): FilterSearchDb {
        return FilterSearchDb(input.drinkId, input.drinkName, input.imageUrl)
    }
}