package com.example.projectone.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.projectone.db.AppDatabase
import com.example.projectone.data.Api.ApiResult
import com.example.projectone.data.models.CryptoModel
import com.example.projectone.data.repositories.CryptoRepository
import com.example.projectone.db.model.CryptoModelDB
import com.example.projectone.utils.ApiErrorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber


class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    var repository: CryptoRepository
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    init {
        val cryptoDao = AppDatabase.getAppDatabase(application).CryptoDao()
        repository = CryptoRepository(cryptoDao)
    }

    // API
    fun getCryptoData(): Flow<ApiResult<CryptoModel>> {
        return flow {
            emit(ApiResult.Loading(null, true))
            try {
                val response = repository.getCrypto()
                if (response.isSuccessful) {
                    val cryptoModel = response.body()
                    emit(ApiResult.Success(cryptoModel))

                    cryptoModel!!.coins.map { rate ->
                        CryptoModelDB(
                            id = 0,
                            currency = rate.currency,
                            currencyRateFromUSD = rate.currencyRateFromUSD,
                            coinName = rate.coinName,
                            coin = rate.coin,
                            regularMarketChange = rate.regularMarketChange,
                            regularMarketPrice = rate.regularMarketPrice,
                            regularMarketChangePercent = rate.regularMarketChangePercent,
                            regularMarketDayLow = rate.regularMarketDayLow,
                            regularMarketDayHigh = rate.regularMarketDayHigh,
                            regularMarketDayRange = rate.regularMarketDayRange,
                            regularMarketVolume = rate.regularMarketVolume,
                            marketCap = rate.marketCap,
                            regularMarketTime = rate.regularMarketTime,
                            coinImageURL = rate.coinImageUrl
                        )
                    }.let { cryptoModelDBList ->
                        val exists = repository.selicExists() > 0
                        if (exists) {
                            repository.updateAll(cryptoModelDBList)
                        } else {
                            repository.insert(cryptoModelDBList)
                        }
                    }
                } else {
                    emit(ApiResult.Error(ApiErrorUtils.getErrorMessage(response.code())))
                }
            } catch (e: Exception) {
                emit(ApiResult.Error("Error getting Crypto data"))
                Timber.e(e)
            }
            emit(ApiResult.Loading(null, false))
        }
    }


    // DB
    fun delete(cryptoModelDB: CryptoModelDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(cryptoModelDB)
        }
    }

    fun getAllCryptos(): LiveData<List<CryptoModelDB>> {
        return repository.getAllCryptos()
    }
}