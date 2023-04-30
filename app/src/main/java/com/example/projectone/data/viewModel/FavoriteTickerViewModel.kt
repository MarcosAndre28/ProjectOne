package com.example.projectone.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.projectone.data.repositories.FavoriteTickerRepository
import com.example.projectone.db.AppDatabase
import com.example.projectone.db.model.FavoriteTickerModelDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FavoriteTickerViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: FavoriteTickerRepository
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    init {
        val favoriteTickerDao = AppDatabase.getAppDatabase(application).FavoriteTickerDao()
        repository = FavoriteTickerRepository(favoriteTickerDao)
    }

    // DB
    fun insert(favoriteTickerModelDB: FavoriteTickerModelDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(
                FavoriteTickerModelDB(
                    favoriteTickerModelDB.id,
                    favoriteTickerModelDB.close,
                    favoriteTickerModelDB.logo,
                    favoriteTickerModelDB.logo
                )
            )
        }
    }


    fun delete(favoriteTickerModelDB: FavoriteTickerModelDB) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(favoriteTickerModelDB)
        }
    }

    fun getAllFavoriteTickers(): LiveData<List<FavoriteTickerModelDB>> {
        return repository.getAllFavoriteTickers()
    }
}