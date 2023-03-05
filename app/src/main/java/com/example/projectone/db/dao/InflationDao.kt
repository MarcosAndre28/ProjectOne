package com.example.projectone.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectone.db.model.InflationModelDB

@Dao
interface InflationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(inflationModelDB: InflationModelDB)

    @Query("SELECT COUNT(*) FROM inflation")
    suspend fun inflationCount(): Int

    @Query("SELECT * FROM inflation")
    fun getInflation(): LiveData<InflationModelDB>

    @Query("SELECT 1 FROM inflation WHERE id = :id")
    fun exists(id: Long): Boolean

    @Delete
    suspend fun delete(inflationModelDB: InflationModelDB)

    @Update
    suspend fun update(inflationModelDB: InflationModelDB)
}