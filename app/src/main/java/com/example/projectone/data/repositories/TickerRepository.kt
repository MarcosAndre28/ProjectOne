package com.example.projectone.data.repositories

import androidx.lifecycle.LiveData
import com.example.projectone.data.Api.Api
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.SelicRate
import com.example.projectone.data.models.TickerModel
import com.example.projectone.db.dao.SelicDao
import com.example.projectone.db.dao.TickerDao
import com.example.projectone.db.model.InflationModelDB
import com.example.projectone.db.model.SelicModelDB
import com.example.projectone.db.model.TickerModelDB
import retrofit2.Response

class TickerRepository constructor(private val tickerDao: TickerDao) {
    private val api = RetrofitInstance.api

    suspend fun getTicker(): Response<TickerModel> {
        return api.getTicker()
    }

    suspend fun insert(tickerModels: List<TickerModelDB>){
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

    suspend fun delete(tickerModelDB: TickerModelDB){
        tickerDao.delete(tickerModelDB)
    }

    fun getAllTickers(): LiveData<List<TickerModelDB>> {
      return   tickerDao.getAllTickers()}
}