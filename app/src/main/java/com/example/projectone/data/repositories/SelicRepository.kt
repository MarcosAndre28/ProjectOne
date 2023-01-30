package com.example.projectone.data.repositories

import androidx.lifecycle.LiveData
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.SelicRate
import com.example.projectone.db.dao.SelicDao
import com.example.projectone.db.model.SelicModel
import retrofit2.Response

class SelicRepository(private val selicDao: SelicDao){
    private val api = RetrofitInstance.api

    suspend fun getSelic(): Response<SelicRate> {
        return api.getSelic()
    }

    suspend fun insert(selicModel: SelicModel){
        selicDao.insert(selicModel)
    }

    suspend fun selicExists(): Int {
        return selicDao.selicCount()
    }

    suspend fun update(selicModel: SelicModel){
        selicDao.update(selicModel)
    }

    suspend fun delete(selicModel: SelicModel){
        selicDao.delete(selicModel)
    }

    fun getSelicDb(): LiveData<SelicModel> {
        return selicDao.getSelic()
    }
}