package com.example.projectone.db.model


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectone.utils.Constants

@Entity(tableName = Constants.CRYPTO_AVAILABLE)
data class CryptoAvailableModelDB(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "coins")
    val coins: String
)