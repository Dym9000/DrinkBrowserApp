package com.example.drinkbrowserapp.util

/**
 * Generic class for holding the state of the data
 */

data class DataState<T>(val state: State, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): DataState<T> {
            return DataState(State.SUCCESS, data, null)
        }

        fun <T> loading(data: T?): DataState<T> {
            return DataState(State.LOADING, data, null)
        }

        fun <T> error(data: T?, errorMessage: String?): DataState<T> {
            return DataState(State.ERROR, data, errorMessage)
        }
    }

}