package com.example.projectone.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.projectone.data.Api.ApiResult
import com.example.projectone.data.models.TickerModel
import com.example.projectone.data.repositories.TickerRepository
import com.example.projectone.db.AppDatabase
import com.example.projectone.db.model.TickerModelDB
import com.example.projectone.utils.ApiErrorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber


class TickerViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: TickerRepository
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    init {
        val tickerDao = AppDatabase.getAppDatabase(application).TickerDao()
        repository = TickerRepository(tickerDao)
    }

    // API
    fun getTickerData(): Flow<ApiResult<TickerModel>> {
        return flow {
            emit(ApiResult.Loading(null, true))
            try {
                val response = repository.getTicker()
                if (response.isSuccessful) {
                    val tickerModel = response.body()
                    emit(ApiResult.Success(tickerModel))

                    tickerModel!!.stocks.map { rate ->
                        TickerModelDB(
                            id = 0,
                            change = rate.change,
                            close = rate.close,
                            logo = rate.logo,
                            market_cap = rate.market_cap,
                            name = rate.name,
                            sector = rate.sector ?: "unknown sector",
                            stock = rate.stock,
                            volume = rate.volume
                        )
                    }.let { tickerModelDBList ->
                        val exists = repository.selicExists() > 0
                        if (exists) {
                            repository.updateAll(tickerModelDBList)
                        } else {
                            repository.insert(tickerModelDBList)
                        }
                    }
                } else {
                    emit(ApiResult.Error(ApiErrorUtils.getErrorMessage(response.code())))
                }
            } catch (e: Exception) {
                emit(ApiResult.Error("Error getting Ticker data"))
                Timber.e(e)
            }
            emit(ApiResult.Loading(null, false))
        }
    }

    // DB
    suspend fun getTickerByName(name: String): TickerModelDB {
        return repository.getTickerByName(name)
    }
    fun delete(tickerModelDB: TickerModelDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(tickerModelDB)
        }
    }

    fun getAllTickers(): LiveData<List<TickerModelDB>> {
        return repository.getAllTickers()
    }
}