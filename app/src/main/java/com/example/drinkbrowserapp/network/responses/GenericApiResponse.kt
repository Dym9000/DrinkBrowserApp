package com.example.drinkbrowserapp.network.api

import retrofit2.Response

sealed class GenericApiResponse<T> {

    companion object {
        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "Unknown error")
        }

        fun <T> create(response: Response<T>): GenericApiResponse<T> {
            if (response.isSuccessful) {
                val body = response.body()
                return if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else if (response.code() == 401) {
                    ApiErrorResponse(
                        "Error 401. Invalid authorization. " +
                                "Check your token or try again later."
                    )
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val errorMessage = response.errorBody()?.string()
                val message = if (errorMessage.isNullOrEmpty()) {
                    response.message()
                } else {
                    errorMessage
                }
                return ApiErrorResponse(errorMessage = message)
            }
        }
    }

}

class ApiEmptyResponse<T> : GenericApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : GenericApiResponse<T>()

data class ApiErrorResponse<T>(val errorMessage: String) : GenericApiResponse<T>()