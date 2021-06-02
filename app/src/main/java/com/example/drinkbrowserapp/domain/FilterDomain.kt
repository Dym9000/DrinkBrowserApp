package com.example.drinkbrowserapp.domain

import com.example.drinkbrowserapp.util.DataState

data class FilterDomain (
    val name: String,
    val filterDomainCriteria: DataState<List<FilterDomainCriteria>>
        )