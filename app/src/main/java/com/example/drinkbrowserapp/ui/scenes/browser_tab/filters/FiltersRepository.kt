package com.example.drinkbrowserapp.ui.scenes.browser_tab.filters

import androidx.lifecycle.LiveData
import com.example.drinkbrowserapp.domain.FilterDomainCriteria
import com.example.drinkbrowserapp.network.api.DrinkService
import com.example.drinkbrowserapp.network.api.GenericApiResponse
import com.example.drinkbrowserapp.network.dto.AlcoholContentRaw
import com.example.drinkbrowserapp.network.dto.CategoryRaw
import com.example.drinkbrowserapp.network.dto.GlassRaw
import com.example.drinkbrowserapp.network.dto.IngredientRaw
import com.example.drinkbrowserapp.network.mapper.database.AlcoholContentDtoMapper
import com.example.drinkbrowserapp.network.mapper.database.CategoryDtoMapper
import com.example.drinkbrowserapp.network.mapper.database.GlassDtoMapper
import com.example.drinkbrowserapp.network.mapper.database.IngredientDtoMapper
import com.example.drinkbrowserapp.network.responses.AlcoholContentResponse
import com.example.drinkbrowserapp.network.responses.CategoryResponse
import com.example.drinkbrowserapp.network.responses.GlassResponse
import com.example.drinkbrowserapp.network.responses.IngredientResponse
import com.example.drinkbrowserapp.persistence.dao.FilterDao
import com.example.drinkbrowserapp.persistence.entity.AlcoholContentDb
import com.example.drinkbrowserapp.persistence.entity.CategoryDb
import com.example.drinkbrowserapp.persistence.entity.GlassDb
import com.example.drinkbrowserapp.persistence.entity.IngredientDb
import com.example.drinkbrowserapp.persistence.mapper.AlcoholContentDbMapper
import com.example.drinkbrowserapp.persistence.mapper.CategoryDbMapper
import com.example.drinkbrowserapp.persistence.mapper.GlassDbMapper
import com.example.drinkbrowserapp.persistence.mapper.IngredientDbMapper
import com.example.drinkbrowserapp.ui.common.repository.NetworkDataStateRepository
import com.example.drinkbrowserapp.util.DataState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FiltersRepository @Inject constructor(
    private val drinkService: DrinkService,
    private val filterDao: FilterDao,
//    private val ingredientNetworkMapper: IngredientDtoMapper,
//    private val ingredientDbMapper: IngredientDbMapper,
//    private val categoryNetworkMapper: CategoryDtoMapper,
//    private val categoryDbMapper: CategoryDbMapper,
//    private val glassNetworkMapper: GlassDtoMapper,
//    private val glassDbMapper: GlassDbMapper,
//    private val alcoholContentNetworkMapper: AlcoholContentDtoMapper,
//    private val alcoholContentDbMapper: AlcoholContentDbMapper
) {

    fun getIngredients(
        key: String,
        query: String
    ): LiveData<DataState<List<FilterDomainCriteria>>> {
        return object :
            NetworkDataStateRepository<IngredientResponse, FilterDomainCriteria,
                    IngredientDb, IngredientRaw>(
                dtoMapper = IngredientDtoMapper(), cacheMapper = IngredientDbMapper()
            ) {

            override fun loadDataFromDatabase(): LiveData<List<IngredientDb>> {
                return filterDao.getIngredients()
            }

            override fun shouldGetNewDataFromNetwork(data: List<IngredientDb>?): Boolean {
                return (data == null || data.isEmpty())
            }

            override fun makeRequestCall(): LiveData<GenericApiResponse<IngredientResponse>> {
                return drinkService.getIngredients(key, query)
            }

            override suspend fun saveDataToDatabase(response: IngredientResponse) {
                filterDao.saveIngredients(mapToCache(response))
            }

            override fun mapToDomain(data: List<IngredientDb>): List<FilterDomainCriteria> {
                return cacheMapper.mapFromList(data)
            }

            override fun mapToCache(data: IngredientResponse): List<IngredientDb> {
                return dtoMapper.mapFromList(data.ingredients)
            }
        }.returnAsLiveData()
    }

    fun getCategories(key: String, query: String): LiveData<DataState<List<FilterDomainCriteria>>> {
        return object :
            NetworkDataStateRepository<CategoryResponse, FilterDomainCriteria, CategoryDb,
                    CategoryRaw>(
                dtoMapper = CategoryDtoMapper(), cacheMapper = CategoryDbMapper()
            ) {
            override fun shouldGetNewDataFromNetwork(data: List<CategoryDb>?): Boolean {
                return (data == null || data.isEmpty())
            }

            override fun makeRequestCall(): LiveData<GenericApiResponse<CategoryResponse>> {
                return drinkService.getCategories(key, query)
            }

            override fun loadDataFromDatabase(): LiveData<List<CategoryDb>> {
                return filterDao.getCategories()
            }

            override suspend fun saveDataToDatabase(response: CategoryResponse) {
                filterDao.saveCategories(mapToCache(response))
            }

            override fun mapToDomain(data: List<CategoryDb>): List<FilterDomainCriteria> {
                return cacheMapper.mapFromList(data)
            }

            override fun mapToCache(data: CategoryResponse): List<CategoryDb> {
                return dtoMapper.mapFromList(data.categories)
            }
        }.returnAsLiveData()
    }

    fun getGlasses(key: String, query: String): LiveData<DataState<List<FilterDomainCriteria>>> {
        return object :
            NetworkDataStateRepository<GlassResponse, FilterDomainCriteria, GlassDb, GlassRaw>(
                dtoMapper = GlassDtoMapper(), cacheMapper = GlassDbMapper()
            ) {
            override fun shouldGetNewDataFromNetwork(data: List<GlassDb>?): Boolean {
                return (data == null || data.isEmpty())
            }

            override fun makeRequestCall(): LiveData<GenericApiResponse<GlassResponse>> {
                return drinkService.getGlassTypes(key, query)
            }

            override fun loadDataFromDatabase(): LiveData<List<GlassDb>> {
                return filterDao.getGlasses()
            }

            override suspend fun saveDataToDatabase(response: GlassResponse) {
                filterDao.saveGlasses(mapToCache(response))
            }

            override fun mapToDomain(data: List<GlassDb>): List<FilterDomainCriteria> {
                return cacheMapper.mapFromList(data)
            }

            override fun mapToCache(data: GlassResponse): List<GlassDb> {
                return dtoMapper.mapFromList(data.glasses)
            }
        }.returnAsLiveData()
    }

    fun getAlcoholContents(
        key: String,
        query: String
    ): LiveData<DataState<List<FilterDomainCriteria>>> {
        return object :
            NetworkDataStateRepository<AlcoholContentResponse, FilterDomainCriteria, AlcoholContentDb,
                    AlcoholContentRaw>(
                dtoMapper = AlcoholContentDtoMapper(), cacheMapper = AlcoholContentDbMapper()
            ) {
            override fun shouldGetNewDataFromNetwork(data: List<AlcoholContentDb>?): Boolean {
                return (data == null || data.isEmpty())
            }

            override fun makeRequestCall(): LiveData<GenericApiResponse<AlcoholContentResponse>> {
                return drinkService.getAlcoholContents(key, query)
            }

            override fun loadDataFromDatabase(): LiveData<List<AlcoholContentDb>> {
                return filterDao.getAlcoholContents()
            }

            override suspend fun saveDataToDatabase(response: AlcoholContentResponse) {
                filterDao.saveAlcoholContents(mapToCache(response))
            }

            override fun mapToDomain(data: List<AlcoholContentDb>): List<FilterDomainCriteria> {
                return cacheMapper.mapFromList(data)
            }

            override fun mapToCache(data: AlcoholContentResponse): List<AlcoholContentDb> {
                return dtoMapper.mapFromList(data.alcoholContents)
            }
        }.returnAsLiveData()
    }

}