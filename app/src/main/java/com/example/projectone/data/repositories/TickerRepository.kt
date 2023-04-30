package com.example.projectone.data.repositories

import androidx.lifecycle.LiveData
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.TickerModel
import com.example.projectone.db.dao.TickerDao
import com.example.projectone.db.model.TickerModelDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TickerRepository constructor(private val tickerDao: TickerDao) {
    private val api = RetrofitInstance.api

    val tickersLiveData: LiveData<List<TickerModelDB>> = tickerDao.getAllTickers()
    val allFavorites: LiveData<List<TickerModelDB>> = tickerDao.getFavoriteTickers()

    suspend fun getTicker(): Response<TickerModel> {
        return api.getTicker()
    }

    suspend fun insert(tickerModels: List<TickerModelDB>) {
        tickerModels.forEach { tickerModel ->
            tickerDao.insert(tickerModel)
        }
    }

    suspend fun selicExists(): Int {
        return tickerDao.tickerCount()
    }

    suspend fun updateAll(tickerModels: List<TickerModelDB>) {
        tickerModels.forEach { tickerModel ->
            tickerDao.update(tickerModel)
        }
    }

    suspend fun getTickerByName(name: String): TickerModelDB {
        return withContext(Dispatchers.IO) {
            tickerDao.getTickerByName(name)
        }
    }

    suspend fun delete(tickerModelDB: TickerModelDB) {
        tickerDao.delete(tickerModelDB)
    }

    fun getAllTickers(): LiveData<List<TickerModelDB>> {
        return tickerDao.getAllTickers()
    }
   suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean) {
         tickerDao.updateFavoriteStatus(id, isFavorite)
    }

    fun getFavoriteTickers(): LiveData<List<TickerModelDB>> {
        return tickerDao.getFavoriteTickers()
    }
}