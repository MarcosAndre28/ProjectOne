package com.example.projectone.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectone.db.model.FavoriteTickerModelDB

@Dao
interface FavoriteTickerDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favoriteTickerModel: FavoriteTickerModelDB)

    @Delete
    suspend fun delete(favoriteTickerModelDB: FavoriteTickerModelDB)

    @Query("SELECT * FROM favorite_ticker")
    fun getAllFavoriteTickers(): LiveData<List<FavoriteTickerModelDB>>

//    @Query("SELECT count(*) FROM favorite_ticker WHERE favorite_ticker.id = :id")
//    suspend fun checkFavorite(id: FavoriteTickerModelDB): Int

}