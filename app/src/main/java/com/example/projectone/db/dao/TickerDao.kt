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

    @Query("SELECT 1 FROM ticker WHERE id = :id")
    fun exists(id: Long): Boolean

    @Delete
    suspend fun delete(tickerModelDB: TickerModelDB)

    @Update
    suspend fun update(tickerModelDB: TickerModelDB)

    @Query("SELECT * FROM ticker")
    fun getAllTickers(): LiveData<List<TickerModelDB>>

}