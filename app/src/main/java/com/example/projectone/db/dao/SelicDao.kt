package com.example.projectone.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.projectone.db.model.SelicModel

@Dao
interface SelicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(selicModel: SelicModel)

    @Query("SELECT COUNT(*) FROM selic")
    suspend fun selicCount(): Int


    @Query("SELECT * FROM selic")
    fun getSelic(): LiveData<SelicModel>

    @Query("SELECT 1 FROM selic WHERE id = :id")
    fun exists(id: Long): Boolean
    @Delete
    suspend fun delete(selicModel: SelicModel)

    @Update
    suspend fun  update(selicModel: SelicModel)

}
