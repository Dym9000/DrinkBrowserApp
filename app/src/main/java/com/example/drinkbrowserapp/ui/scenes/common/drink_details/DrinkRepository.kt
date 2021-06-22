package com.example.drinkbrowserapp.ui.scenes.common.drink_details

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.domain.DrinkDomain
import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.network.api.GenericApiResponse
import com.example.drinkbrowserapp.network.dto.DrinkRaw
import com.example.drinkbrowserapp.network.mapper.database.DrinkDtoMapper
import com.example.drinkbrowserapp.network.responses.SearchByIdOrNameDrinkResponse
import com.example.drinkbrowserapp.persistence.dao.DrinksDao
import com.example.drinkbrowserapp.persistence.entity.DrinkDb
import com.example.drinkbrowserapp.persistence.mapper.DrinkDbMapper
import com.example.drinkbrowserapp.ui.common.NetworkDataStateRepository
import com.example.drinkbrowserapp.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DrinkRepository @Inject constructor(
    private val drinksDao: DrinksDao,
    private val drinksService: DrinkService
) {

    var queryId: Int = -1
    var queryName: String = ""

    fun getDrinkDetails(key: String, drinkId: Int): LiveData<DataState<List<DrinkDomain>>> {
        return object : NetworkDataStateRepository<SearchByIdOrNameDrinkResponse, DrinkDomain,
                DrinkDb, DrinkRaw>(dtoMapper = DrinkDtoMapper(), cacheMapper = DrinkDbMapper()) {
            override fun shouldGetNewDataFromNetwork(data: List<DrinkDb>?): Boolean {
                if (drinkId != queryId) {
                    queryId = drinkId
                    GlobalScope.launch {
                        withContext(Dispatchers.IO) {
                                val isInDatabase = drinksDao.isDrinkInDatabase(queryId)
                                isInDatabase != 1
                        }
                    }
                }
                return data.isNullOrEmpty()
            }

            override fun makeRequestCall(): LiveData<GenericApiResponse<SearchByIdOrNameDrinkResponse>> {
                return drinksService.getDrinkDetailsById(key, drinkId.toString())
            }

            override fun loadDataFromDatabase(): LiveData<List<DrinkDb>> {
                return drinksDao.getDrinkDetails(drinkId)
            }

            override suspend fun saveDataToDatabase(response: SearchByIdOrNameDrinkResponse) {
                drinksDao.saveDrinksByNameResult(mapToCache(response))
            }

            override fun mapToDomain(data: List<DrinkDb>): List<DrinkDomain> {
                return cacheMapper.mapFromList(data)
            }

            override fun mapToCache(data: SearchByIdOrNameDrinkResponse): List<DrinkDb> {
                return data.drinks?.let { dtoMapper.mapFromList(it) }!!
            }
        }.returnAsLiveData()
    }

    fun getDrinksByName(key: String, query: String): LiveData<DataState<List<DrinkDomain>>> {
        return object : NetworkDataStateRepository<SearchByIdOrNameDrinkResponse, DrinkDomain,
                DrinkDb, DrinkRaw>(dtoMapper = DrinkDtoMapper(), cacheMapper = DrinkDbMapper()) {
            override fun shouldGetNewDataFromNetwork(data: List<DrinkDb>?): Boolean {
                if (query != queryName) {
                    queryName = query
                    GlobalScope.launch {
                        withContext(Dispatchers.IO) {
                            drinksDao.clearDrinksByName()
                        }
                    }
                    return true
                }
                return data.isNullOrEmpty()
            }

            override fun makeRequestCall(): LiveData<GenericApiResponse<SearchByIdOrNameDrinkResponse>> {
                return drinksService.getDrinksByName(key, query)
            }

            override fun loadDataFromDatabase(): LiveData<List<DrinkDb>> {
                return drinksDao.getAllDrinksByName()
            }

            override suspend fun saveDataToDatabase(response: SearchByIdOrNameDrinkResponse) {
                if(response.drinks == null){
                    drinksDao.clearDrinksByName()
                }else {
                    drinksDao.saveDrinksByNameResult(mapToCache(response))
                }
            }

            override fun mapToDomain(data: List<DrinkDb>): List<DrinkDomain> {
                return cacheMapper.mapFromList(data)
            }

            override fun mapToCache(data: SearchByIdOrNameDrinkResponse): List<DrinkDb> {
                if(data.drinks != null){
                    return data.drinks?.let { dtoMapper.mapFromList(it) }!!
                }
                return emptyList()
            }
        }.returnAsLiveData()
    }

}