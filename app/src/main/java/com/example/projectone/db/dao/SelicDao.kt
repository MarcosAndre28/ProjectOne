package com.example.projectone.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectone.db.model.Selic

@Dao
interface SelicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selic: Selic)

    @Query("SELECT COUNT(*) FROM selic")
    suspend fun selicCount(): Int


    @Query("SELECT * FROM selic")
    fun getSelic(): LiveData<Selic>

    @Query("SELECT 1 FROM selic WHERE id = :id")
    fun exists(id: Long): Boolean
    @Delete
    suspend fun delete(selic: Selic)

    @Update
    suspend fun  update(selic: Selic)

}
