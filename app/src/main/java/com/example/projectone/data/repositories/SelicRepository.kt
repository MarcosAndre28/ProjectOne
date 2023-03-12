package com.example.projectone.data.repositories

import androidx.lifecycle.LiveData
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.SelicRate
import com.example.projectone.db.dao.SelicDao
import com.example.projectone.db.model.SelicModelDB
import retrofit2.Response

class SelicRepository(private val selicDao: SelicDao) {
    private val api = RetrofitInstance.api

    suspend fun getSelic(): Response<SelicRate> {
        return api.getSelic()
    }

    suspend fun insert(selicModelDB: SelicModelDB) {
        selicDao.insert(selicModelDB)
    }

    suspend fun selicExists(): Int {
        return selicDao.selicCount()
    }

    suspend fun update(selicModelDB: SelicModelDB) {
        selicDao.update(selicModelDB)
    }

    suspend fun delete(selicModelDB: SelicModelDB) {
        selicDao.delete(selicModelDB)
    }

    fun getSelicDb(): LiveData<SelicModelDB> {
        return selicDao.getSelic()
    }
}