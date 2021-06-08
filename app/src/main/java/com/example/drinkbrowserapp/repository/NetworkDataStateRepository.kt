package com.example.drinkbrowserapp.repository


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

abstract class NetworkDataStateRepository<NetworkResponse, DomainList, CacheList,
        NetworkModel, CacheModel, DomainModel>(
    val dtoMapper: GenericMapper<NetworkModel, CacheModel>,
    val cacheMapper: GenericMapper<CacheModel, DomainModel>
) {

    protected val result = MediatorLiveData<DataState<DomainList>>()

    init {
        result.value = DataState.loading(null)

        @Suppress("LeakingThis")
        val databaseSource = loadDataFromDatabase()

        result.addSource(databaseSource) {
            result.removeSource(databaseSource)
            if (shouldGetNewDataFromNetwork(it)) {
//                GlobalScope.launch(IO) {
//                    withContext(Main) {
                getDataFromNetwork(databaseSource)
//                    }
//                }
            } else {
                result.addSource(databaseSource) { cacheList ->
                    setValue(DataState.success(mapToDomain(cacheList)))
                }
            }
        }
    }

    private fun getDataFromNetwork(databaseSource: LiveData<CacheList>) {
        val response = makeRequestCall()
        result.addSource(databaseSource) { cacheList ->
            setValue(DataState.loading(mapToDomain(cacheList)))
        }
        result.addSource(response) {
            result.apply {
                removeSource(databaseSource)
                removeSource(response)
                handleRequestCall(it, databaseSource)
            }
        }

    }

    private fun setValue(newData: DataState<DomainList>) {
        if (result.value != newData)
            result.value = newData
    }

    private fun handleRequestCall(
        apiResponse: GenericApiResponse<NetworkResponse>,
        databaseSource: LiveData<CacheList>
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
        databaseSource: LiveData<CacheList>
    ) {
        GlobalScope.launch {
            withContext(IO) {
                saveDataToDatabase(response.body)
            }
            withContext(Main) {
                result.addSource(databaseSource) { cacheList ->
                    setValue(DataState.success(mapToDomain(cacheList)))
                }
            }
        }
    }

    private fun onFailureResponse(errorMessage: String, databaseSource: LiveData<CacheList>) {
        GlobalScope.launch {
            withContext(Main) {
                result.addSource(databaseSource) { cacheList ->
                    setValue(DataState.error(mapToDomain(cacheList), errorMessage))
                }
            }
        }
    }

    protected abstract fun shouldGetNewDataFromNetwork(data: CacheList?): Boolean

    protected abstract fun makeRequestCall(): LiveData<GenericApiResponse<NetworkResponse>>

    protected abstract fun loadDataFromDatabase(): LiveData<CacheList>

    protected abstract suspend fun saveDataToDatabase(response: NetworkResponse)

    protected abstract fun mapToDomain(data: CacheList): DomainList

    protected abstract fun mapToCache(data: NetworkResponse): CacheList

    fun returnAsLiveData() = result as LiveData<DataState<DomainList>>
}