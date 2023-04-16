package com.example.projectone.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectone.utils.Constants.Companion.FAVORITE_TICKER

@Entity(tableName = FAVORITE_TICKER)
data class FavoriteTickerModelDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "close")
    val close: Double,

    @ColumnInfo(name = "logo")
    val logo: String,

    @ColumnInfo(name = "stock")
    val stock: String,

)

