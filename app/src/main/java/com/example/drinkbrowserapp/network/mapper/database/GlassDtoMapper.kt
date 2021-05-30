package com.example.drinkbrowserapp.network.mapper.database

import com.example.drinkbrowserapp.network.dto.GlassRaw
import com.example.drinkbrowserapp.persistence.entity.GlassDb
import com.example.drinkbrowserapp.util.mapper.GenericMapper

class GlassDtoMapper : GenericMapper<GlassRaw, GlassDb>() {
    override fun mapFrom(input: GlassRaw): GlassDb {
        return GlassDb(
            input.strGlass.orEmpty()
        )
    }

    override fun mapTo(input: GlassDb): GlassRaw {
        return GlassRaw(input.glassType)
    }
}