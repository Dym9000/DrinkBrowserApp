package com.example.drinkbrowserapp.persistence.mapper

import com.example.drinkbrowserapp.domain.GlassDomain
import com.example.drinkbrowserapp.persistence.entity.GlassDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper

class GlassDbMapper: GenericMapper<GlassDb, GlassDomain>(){
    override fun mapFrom(input: GlassDb): GlassDomain {
        return GlassDomain(input.glassType)
    }

    override fun mapTo(input: GlassDomain): GlassDb {
        return GlassDb(input.glassType)
    }
}