package com.example.projectone.data.repositories

import androidx.lifecycle.LiveData
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.CryptoModel
import com.example.projectone.db.dao.CryptoDao
import com.example.projectone.db.model.CryptoModelDB
import retrofit2.Response

class CryptoRepository constructor(private val cryptoDao: CryptoDao) {
    private val api = RetrofitInstance.api
    suspend fun getCrypto(): Response<CryptoModel> {
        return api.getCrypto()
    }



    suspend fun insert(cryptoModels: List<CryptoModelDB>) {
        cryptoModels.forEach { cryptoModel ->
            cryptoDao.insert(cryptoModel)
        }
    }

    suspend fun selicExists(): Int {
        return cryptoDao.cryptoCount()
    }

    suspend fun updateAll(cryptoModels: List<CryptoModelDB>) {
        cryptoModels.forEach { cryptoModel ->
            cryptoDao.update(cryptoModel)
        }
    }

    suspend fun delete(cryptoModelDB: CryptoModelDB) {
        cryptoDao.delete(cryptoModelDB)
    }

    fun getAllCryptos(): LiveData<List<CryptoModelDB>> {
        return cryptoDao.getAllCryptos()
    }
}