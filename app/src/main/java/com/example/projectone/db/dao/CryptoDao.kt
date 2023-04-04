package com.example.projectone.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectone.db.model.CryptoAvailableModelDB
import com.example.projectone.db.model.CryptoModelDB
import com.example.projectone.db.model.TickerModelDB

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cryptoModel: CryptoModelDB)

    @Query("SELECT COUNT(*) FROM crypto")
    suspend fun cryptoCount(): Int

    @Query("SELECT COUNT(*) FROM crypto_available WHERE coins = :coin")
    fun checkCryptoAvailableExists(coin: String): Int

    @Query("SELECT * FROM crypto_available WHERE coins = :name")
    fun getTickerByName(name: String): CryptoAvailableModelDB

    @Query("SELECT 1 FROM crypto WHERE id = :id")
    fun exists(id: Long): Boolean

    @Delete
    suspend fun delete(cryptoModelDB: CryptoModelDB)

    @Update
    suspend fun update(cryptoModelDB: CryptoModelDB)

    @Update
    suspend fun updateAvailable(cryptoAvailable: CryptoAvailableModelDB)

    @Query("SELECT * FROM crypto")
    fun getAllCryptos(): LiveData<List<CryptoModelDB>>

    @Query("SELECT * FROM crypto_available")
    fun getAllCryptoAvailable(): LiveData<List<CryptoAvailableModelDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCryptoAvailable(cryptoAvailable: CryptoAvailableModelDB)

}