package com.example.drinkbrowserapp.ui.scenes.browser_tab.filters

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.domain.FilterDomainCriteria
import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.network.api.GenericApiResponse
import com.example.drinkbrowserapp.network.mapper.database.IngredientDtoMapper
import com.example.drinkbrowserapp.network.responses.IngredientResponse
import com.example.drinkbrowserapp.persistence.dao.FilterDao
import com.example.drinkbrowserapp.persistence.entity.IngredientDb
import com.example.drinkbrowserapp.persistence.mapper.FilterMapper
import com.example.drinkbrowserapp.repository.NetworkDataStateRepository
import com.example.drinkbrowserapp.util.DataState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FiltersRepository @Inject constructor(
    private val drinkService: DrinkService,
    private val filterDao: FilterDao,
    private val ingredientNetworkMapper: IngredientDtoMapper,
    private val ingredientDbMapper: FilterMapper
) {

    fun getIngredients(key: String, query: String): LiveData<DataState<List<FilterDomainCriteria>>> {
        return object :
            NetworkDataStateRepository<IngredientResponse, List<FilterDomainCriteria>, List<IngredientDb>>() {

            override fun loadDataFromDatabase(): LiveData<List<IngredientDb>> {
                return filterDao.getIngredients()
            }

            override fun shouldGetNewDataFromNetwork(data: List<IngredientDb>?): Boolean {
                return data == null
            }

            override suspend fun makeRequestCall(): LiveData<GenericApiResponse<IngredientResponse>> {
                return drinkService.getIngredients(key, query)
            }

            override suspend fun saveDataToDatabase(response: IngredientResponse) {
                filterDao.saveIngredients(mapToCache(response))
            }

            override fun mapToDomain(data: List<IngredientDb>): List<FilterDomainCriteria> {
                return ingredientDbMapper.mapFromList(data)
            }

            override fun mapToCache(data: IngredientResponse): List<IngredientDb> {
                return ingredientNetworkMapper.mapFromList(data.ingredients)
            }
        }.returnAsLiveData()
    }
}