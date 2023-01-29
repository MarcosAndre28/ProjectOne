package com.example.projectone.data.repositories

import androidx.lifecycle.LiveData
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.SelicRate
import com.example.projectone.data.viewModel.SelicViewModel
import com.example.projectone.db.dao.SelicDao
import com.example.projectone.db.model.Selic
import retrofit2.Response
import retrofit2.await

class SelicRepository(private val selicDao: SelicDao){
    private val api = RetrofitInstance.api

    suspend fun getSelic(): Response<SelicRate> {
        return api.getSelic()
    }

    suspend fun insert(selic: Selic){
        selicDao.insert(selic)
    }

    suspend fun selicExists(): Int {
        return selicDao.selicCount()
    }

    suspend fun update(selic: Selic){
        selicDao.update(selic)
    }

    suspend fun delete(selic: Selic){
        selicDao.delete(selic)
    }

    fun getSelicDb(): LiveData<Selic> {
        return selicDao.getSelic()
    }

}