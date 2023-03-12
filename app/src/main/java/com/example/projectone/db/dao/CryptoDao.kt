package com.example.projectone.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectone.db.model.CryptoModelDB

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cryptoModel: CryptoModelDB)

    @Query("SELECT COUNT(*) FROM crypto")
    suspend fun cryptoCount(): Int

    @Query("SELECT 1 FROM crypto WHERE id = :id")
    fun exists(id: Long): Boolean

    @Delete
    suspend fun delete(cryptoModelDB: CryptoModelDB)

    @Update
    suspend fun update(cryptoModelDB: CryptoModelDB)

    @Query("SELECT * FROM crypto")
    fun getAllCryptos(): LiveData<List<CryptoModelDB>>

}