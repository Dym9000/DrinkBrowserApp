package com.example.drinkbrowserapp.util.mapper

interface GenericMapper<I,O> {
    fun mapFromList(input: List<I>):List<O>
    fun mapFrom(input: I):O
    fun mapToList(input:List<O>):List<I>
    fun mapTo(input:O):I
}