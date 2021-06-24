package com.example.drinkbrowserapp.ui.common.interfaces

import com.example.drinkbrowserapp.util.DataState

interface UIStateListener {
    fun onDataStateChanged(dataState: DataState<*>?)
}