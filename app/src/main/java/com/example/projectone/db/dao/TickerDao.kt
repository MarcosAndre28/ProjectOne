package com.example.projectone.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectone.db.model.TickerModelDB

@Dao
interface TickerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tickerModel: TickerModelDB)

    @Query("SELECT COUNT(*) FROM ticker")
    suspend fun tickerCount(): Int

    @Query("SELECT * FROM ticker WHERE name = :name")
    fun getTickerByName(name: String): TickerModelDB

    @Query("SELECT 1 FROM ticker WHERE id = :id")
    fun exists(id: Long): Boolean

    @Delete
    suspend fun delete(tickerModelDB: TickerModelDB)

    @Update
    suspend fun update(tickerModelDB: TickerModelDB)

    @Query("SELECT * FROM ticker WHERE isFavorite = 0 ORDER BY id ASC LIMIT 30")
    fun getAllTickers(): LiveData<List<TickerModelDB>>

    @Query("UPDATE ticker SET isFavorite = :isFavorite WHERE id = :id")
    suspend fun updateFavoriteStatus(id: Long, isFavorite: Boolean)

    @Query("SELECT * FROM ticker WHERE isFavorite = 1")
    fun getFavoriteTickers(): LiveData<List<TickerModelDB>>

}