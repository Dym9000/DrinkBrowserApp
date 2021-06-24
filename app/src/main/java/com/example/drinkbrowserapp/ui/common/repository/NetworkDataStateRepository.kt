package com.example.drinkbrowserapp.ui.common.repository


import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.drinkbrowserapp.network.api.ApiEmptyResponse
import com.example.drinkbrowserapp.network.api.ApiErrorResponse
import com.example.drinkbrowserapp.network.api.ApiSuccessResponse
import com.example.drinkbrowserapp.network.api.GenericApiResponse
import com.example.drinkbrowserapp.util.DataState
import com.example.drinkbrowserapp.util.mapper.GenericMapper
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *Abstract class providing methods for network services and databases
 * DomainList is an object presented to the user
 * NetworkResponse is an object requested from network
 */

abstract class NetworkDataStateRepository<NetworkResponse, DomainModel, CacheModel, NetworkModel>(
    val dtoMapper: GenericMapper<NetworkModel, CacheModel>,
    val cacheMapper: GenericMapper<CacheModel, DomainModel>
) {

    protected val result = MediatorLiveData<DataState<List<DomainModel>>>()

    init {
        result.value = DataState.loading(null)

        @Suppress("LeakingThis")
        val databaseSource = loadDataFromDatabase()

        result.addSource(databaseSource) {
            result.removeSource(databaseSource)
            if (shouldGetNewDataFromNetwork(it)) {
                getDataFromNetwork(databaseSource)
            } else {
                result.addSource(databaseSource) { cacheList ->
                    setValue(DataState.success(mapToDomain(cacheList)))
                }
            }
        }
    }

    private fun getDataFromNetwork(databaseSource: LiveData<List<CacheModel>>) {
        val response = makeRequestCall()
//        result.addSource(databaseSource) { cacheList ->
//            setValue(DataState.loading(mapToDomain(cacheList)))
//        }
        result.addSource(response) {
            result.apply {
//                removeSource(databaseSource)
                removeSource(response)
                handleRequestCall(it, databaseSource)
            }
        }

    }

    private fun setValue(newData: DataState<List<DomainModel>>) {
        if (result.value != newData)
            result.value = newData
    }

    private fun handleRequestCall(
        apiResponse: GenericApiResponse<NetworkResponse>,
        databaseSource: LiveData<List<CacheModel>>
    ) {
        when (apiResponse) {
            is ApiSuccessResponse ->
                onSuccessResponse(apiResponse, databaseSource)
            is ApiEmptyResponse ->
                onFailureResponse("HTTP 204. There was an empty response", databaseSource)
            is ApiErrorResponse ->
                onFailureResponse(apiResponse.errorMessage, databaseSource)
            else -> onFailureResponse("ApiResponse not recognized.", databaseSource)
        }
    }

    private fun onSuccessResponse(
        response: ApiSuccessResponse<NetworkResponse>,
        databaseSource: LiveData<List<CacheModel>>
    ){
            GlobalScope.launch {
                withContext(IO) {
                    saveDataToDatabase(response.body)
                }

            GlobalScope.launch {
                withContext(Main) {
                    result.addSource(databaseSource) { cacheList ->
                        setValue(DataState.success(mapToDomain(cacheList)))
                    }
                }
            }
        }
    }

    private fun onFailureResponse(
        errorMessage: String,
        databaseSource: LiveData<List<CacheModel>>
    ) {
        GlobalScope.launch {
            withContext(Main) {
                result.addSource(databaseSource) { cacheList ->
                    setValue(DataState.error(mapToDomain(cacheList), errorMessage))
                }
            }
        }
    }

    protected abstract fun shouldGetNewDataFromNetwork(data: List<CacheModel>?): Boolean

    protected abstract fun makeRequestCall(): LiveData<GenericApiResponse<NetworkResponse>>

    protected abstract fun loadDataFromDatabase(): LiveData<List<CacheModel>>

    protected abstract suspend fun saveDataToDatabase(response: NetworkResponse)

    protected abstract fun mapToDomain(data: List<CacheModel>): List<DomainModel>

    protected abstract fun mapToCache(data: NetworkResponse): List<CacheModel>

    fun returnAsLiveData() = result as LiveData<DataState<List<DomainModel>>>
}