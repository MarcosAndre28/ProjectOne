package com.example.projectone.data.repositories

import androidx.lifecycle.LiveData
import com.example.projectone.db.dao.FavoriteTickerDao
import com.example.projectone.db.model.FavoriteTickerModelDB

class FavoriteTickerRepository(private val favoriteTickerDao: FavoriteTickerDao) {

    suspend fun insert(favoriteTickerModelDB: FavoriteTickerModelDB) {
        favoriteTickerDao.insert(favoriteTickerModelDB)
    }

    suspend fun delete(favoriteTickerModelDB: FavoriteTickerModelDB) {
        favoriteTickerDao.delete(favoriteTickerModelDB)
    }

    fun getAllFavoriteTickers(): LiveData<List<FavoriteTickerModelDB>> {
        return favoriteTickerDao.getAllFavoriteTickers()
    }

//    suspend fun checkFavorite(favoriteTickerModelDB : FavoriteTickerModelDB) : Int{
//        return favoriteTickerDao.checkFavorite(favoriteTickerModelDB)
//    }

}