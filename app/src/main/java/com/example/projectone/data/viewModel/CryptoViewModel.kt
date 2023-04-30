package com.example.projectone.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectone.data.models.CryptoModel
import com.example.projectone.data.repositories.CryptoRepository
import com.example.projectone.db.AppDatabase
import com.example.projectone.db.model.CryptoAvailableModelDB
import com.example.projectone.db.model.CryptoModelDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CryptoViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: CryptoRepository
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    private val _cryptoData = MutableLiveData<CryptoModel>()
    val cryptoData: LiveData<CryptoModel> = _cryptoData

    init {
        val cryptoDao = AppDatabase.getAppDatabase(application).CryptoDao()
        repository = CryptoRepository(cryptoDao)
    }

    fun getCryptoAvailable() {
        viewModelScope.launch {
            val response = repository.getCryptoAvailable()
            if (response.isSuccessful) {
                val coins = response.body()?.coins ?: emptyList()
                coins.forEach { coin ->
                    val cryptoAvailableModelDB = CryptoAvailableModelDB(coins = coin)
                    val exists = withContext(Dispatchers.IO) { repository.availableExists(coin) } > 0
                    if (exists) {
                        withContext(Dispatchers.IO) { repository.updateAllAvailable(cryptoAvailableModelDB) }
                    } else {
                        withContext(Dispatchers.IO) { repository.insertCryptoAvailable(cryptoAvailableModelDB) }
                    }
                }
            }
        }
    }

    fun getCryptoData(coin: String, currency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCrypto(coin, currency)
            _cryptoData.postValue(response)
        }
    }

        suspend fun getTickerByName(name: String): CryptoAvailableModelDB {
            return repository.getTickerByName(name)
        }

        fun delete(cryptoModelDB: CryptoModelDB) {
            viewModelScope.launch(Dispatchers.IO) {
                repository.delete(cryptoModelDB)
            }
        }

        fun getAllCryptos(): LiveData<List<CryptoModelDB>> {
            return repository.getAllCryptos()
        }

        fun getAllCryptoAvailable(): LiveData<List<CryptoAvailableModelDB>> {
            return repository.getAllCryptoAvailable()
        }
    }