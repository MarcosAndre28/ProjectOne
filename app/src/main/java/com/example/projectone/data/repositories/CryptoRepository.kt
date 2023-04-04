package com.example.projectone.data.repositories

import androidx.lifecycle.LiveData
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.CryptoAvailableModel
import com.example.projectone.data.models.CryptoModel
import com.example.projectone.db.dao.CryptoDao
import com.example.projectone.db.model.CryptoAvailableModelDB
import com.example.projectone.db.model.CryptoModelDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CryptoRepository constructor(private val cryptoDao: CryptoDao) {
    private val api = RetrofitInstance.api

     suspend fun getCrypto(coin: String, currency: String): Response<CryptoModel>{
         return api.getCrypto(coin, currency)
    }

     suspend fun getCryptoAvailable(): Response<CryptoAvailableModel>{
        return  api.getCryptoAvailable()
    }
    suspend fun insert(cryptoModels: List<CryptoModelDB>) {
        cryptoModels.forEach { cryptoModel ->
            cryptoDao.insert(cryptoModel)
        }
    }

    suspend fun insertCryptoAvailable(cryptoAvailable: CryptoAvailableModelDB){
        cryptoDao.insertCryptoAvailable(cryptoAvailable)
    }

    suspend fun selicExists(): Int {
        return cryptoDao.cryptoCount()
    }

    suspend fun availableExists(coin: String): Int{
        return cryptoDao.checkCryptoAvailableExists(coin)
    }

    suspend fun updateAll(cryptoModels: List<CryptoModelDB>) {
        cryptoModels.forEach { cryptoModel ->
            cryptoDao.update(cryptoModel)
        }
    }

    suspend fun getTickerByName(name: String): CryptoAvailableModelDB {
        return withContext(Dispatchers.IO) {
            cryptoDao.getTickerByName(name)
        }
    }

    suspend fun updateAllAvailable(cryptoAvailable: CryptoAvailableModelDB) {
            cryptoDao.updateAvailable(cryptoAvailable)
        }

    suspend fun delete(cryptoModelDB: CryptoModelDB) {
        cryptoDao.delete(cryptoModelDB)
    }

     fun getAllCryptos(): LiveData<List<CryptoModelDB>> {
        return cryptoDao.getAllCryptos()
    }

     fun getAllCryptoAvailable(): LiveData<List<CryptoAvailableModelDB>>{
        return  cryptoDao.getAllCryptoAvailable()
    }
}