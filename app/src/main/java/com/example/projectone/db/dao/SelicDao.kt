package com.example.projectone.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectone.db.model.SelicModelDB

@Dao
interface SelicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selicModelDB: SelicModelDB)

    @Query("SELECT COUNT(*) FROM selic")
    suspend fun selicCount(): Int


    @Query("SELECT * FROM selic")
    fun getSelic(): LiveData<SelicModelDB>

    @Query("SELECT 1 FROM selic WHERE id = :id")
    fun exists(id: Long): Boolean
    @Delete
    suspend fun delete(selicModelDB: SelicModelDB)

    @Update
    suspend fun  update(selicModelDB: SelicModelDB)

}
