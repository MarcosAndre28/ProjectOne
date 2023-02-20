package com.example.projectone.data.repositories

import androidx.lifecycle.LiveData
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.Inflation
import com.example.projectone.db.dao.InflationDao
import com.example.projectone.db.model.InflationModelDB
import retrofit2.Response

class InflationRepository(private val inflationDao: InflationDao){
    private val api = RetrofitInstance.api

    suspend fun getInflation(): Response<Inflation> {
        return api.getInflation()
    }

    suspend fun insert(inflationModelDB: InflationModelDB){
        inflationDao.insert(inflationModelDB)
    }

    suspend fun inflationExists(): Int {
        return inflationDao.inflationCount()
    }

    suspend fun update(inflationModelDB: InflationModelDB){
        inflationDao.update(inflationModelDB)
    }

    fun getSelicDb(): LiveData<InflationModelDB> {
        return inflationDao.getInflation()
    }
}