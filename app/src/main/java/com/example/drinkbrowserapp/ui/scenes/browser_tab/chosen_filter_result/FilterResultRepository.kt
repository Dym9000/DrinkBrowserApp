package com.example.drinkbrowserapp.ui.scenes.browser_tab.chosen_filter_result

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.domain.FilterSearchDomain
import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.network.api.GenericApiResponse
import com.example.drinkbrowserapp.network.dto.FilterSearchRaw
import com.example.drinkbrowserapp.network.mapper.database.FilterSearchDtoMapper
import com.example.drinkbrowserapp.network.responses.FilterSearchResponse
import com.example.drinkbrowserapp.persistence.dao.DrinksDao
import com.example.drinkbrowserapp.persistence.entity.FilterSearchDb
import com.example.drinkbrowserapp.persistence.mapper.FilterSearchDbMapper
import com.example.drinkbrowserapp.repository.NetworkDataStateRepository
import com.example.drinkbrowserapp.util.DataState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FilterResultRepository @Inject constructor(
    private val drinkService: DrinkService,
    private val drinksDao: DrinksDao
) {

    private var query = ""

    fun getIngredients(key: String, ingredient: String)
            : LiveData<DataState<List<FilterSearchDomain>>> {
        return object : NetworkDataStateRepository<FilterSearchResponse, List<FilterSearchDomain>,
                List<FilterSearchDb>, FilterSearchRaw, FilterSearchDb, FilterSearchDomain>
            (dtoMapper = FilterSearchDtoMapper(), cacheMapper = FilterSearchDbMapper()) {

            override fun shouldGetNewDataFromNetwork(data: List<FilterSearchDb>?): Boolean {
                if(ingredient != query) {
                    query = ingredient
                    GlobalScope.launch {
                        withContext(Dispatchers.IO){
                            drinksDao.clearDrinksByFilter()
                        }
                    }
                    return true
                }
                return data.isNullOrEmpty()
            }

            override fun makeRequestCall(): LiveData<GenericApiResponse<FilterSearchResponse>> {
                return drinkService.getDrinksByIngredient(key, ingredient)
            }

            override fun loadDataFromDatabase(): LiveData<List<FilterSearchDb>> {
                return drinksDao.getAllDrinksByFilter()
            }

            override suspend fun saveDataToDatabase(response: FilterSearchResponse) {
                drinksDao.saveFilterSearchResult(mapToCache(response))
            }

            override fun mapToDomain(data: List<FilterSearchDb>): List<FilterSearchDomain> {
                return cacheMapper.mapFromList(data)
            }

            override fun mapToCache(data: FilterSearchResponse): List<FilterSearchDb> {
                return dtoMapper.mapFromList(data.drinks)
            }
        }.returnAsLiveData()
    }
}