package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.FilterDomainCriteria
import com.example.drinkbrowserapp.persistence.entity.GlassDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlassDbMapper @Inject constructor() : GenericMapper<GlassDb, FilterDomainCriteria>() {
    override fun mapFrom(input: GlassDb): FilterDomainCriteria {
        return FilterDomainCriteria(input.glassType)
    }

    override fun mapTo(input: FilterDomainCriteria): GlassDb {
        return GlassDb(input.name)
    }
}