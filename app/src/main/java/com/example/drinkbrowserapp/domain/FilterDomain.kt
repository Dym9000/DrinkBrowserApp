package com.example.drinkbrowserapp.domain

import com.example.drinkbrowserapp.util.DataState

data class FilterDomain(
    val name: String,
    val filterDomainCriteria: DataState<List<FilterDomainCriteria>>
) {
    override fun equals(other: Any?): Boolean {
        if (other?.javaClass != javaClass)
            return false
        other as FilterDomain
        return filterDomainCriteria.data == other.filterDomainCriteria
    }
}