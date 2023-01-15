package com.example.projectone.db.dao

import androidx.room.*
import com.example.projectone.db.model.Selic

@Dao
interface SelicDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun Insert(selic: Selic)
}