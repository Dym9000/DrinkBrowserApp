package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.GlassDomain
import com.example.drinkbrowserapp.persistence.entity.GlassDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GlassDbMapper @Inject constructor() : GenericMapper<GlassDb, GlassDomain>() {
    override fun mapFrom(input: GlassDb): GlassDomain {
        return GlassDomain(input.glassType)
    }

    override fun mapTo(input: GlassDomain): GlassDb {
        return GlassDb(input.glassType)
    }
}