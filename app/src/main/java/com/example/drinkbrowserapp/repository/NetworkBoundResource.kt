package com.example.drinkbrowserapp.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.drinkbrowserapp.network.api.ApiEmptyResponse
import com.example.drinkbrowserapp.network.api.ApiErrorResponse
import com.example.drinkbrowserapp.network.api.ApiSuccessResponse
import com.example.drinkbrowserapp.network.api.GenericApiResponse
import com.example.drinkbrowserapp.util.DataState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 *Abstract class providing methods for network services and databases
 * ResultType is an object presented to the user
 * RequestType is a raw
 */

abstract class NetworkBoundResource<RequestType, ResultType> {

    private val result = MediatorLiveData<DataState<ResultType>>()

    init{
        result.value = DataState.loading(null)

        GlobalScope.launch(IO){
            val apiResponse = makeRequestCall()
            result.addSource(apiResponse){
                result.removeSource(apiResponse)
                handleRequestCall(it)
            }
        }

    }

    private fun handleRequestCall(apiResponse: GenericApiResponse<RequestType>){
        when (apiResponse){
            is ApiSuccessResponse ->
                onSuccessResponse(apiResponse)
            is ApiEmptyResponse ->
                onFailureResponse("HTTP 204. There was an empty response")
            is ApiErrorResponse ->
                onFailureResponse(apiResponse.errorMessage)
            else -> onFailureResponse("ApiResponse not recognized.")
        }
    }

    abstract fun onFailureResponse(message: String)

    abstract fun onSuccessResponse(response: GenericApiResponse<RequestType>)

    abstract suspend fun makeRequestCall():LiveData<GenericApiResponse<RequestType>>

    fun returnAsLiveData() = result as LiveData<DataState<ResultType>>
}