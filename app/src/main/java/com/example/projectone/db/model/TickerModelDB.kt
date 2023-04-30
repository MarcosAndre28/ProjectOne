package com.example.projectone.db.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectone.utils.Constants.Companion.TICKER
import kotlinx.parcelize.Parcelize

@Entity(tableName = TICKER)
data class TickerModelDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "change")
    val change: Double,

    @ColumnInfo(name = "close")
    val close: Double,

    @ColumnInfo(name = "logo")
    val logo: String,

    @ColumnInfo(name = "market_cap")
    val market_cap: Double,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "sector")
    val sector: String,

    @ColumnInfo(name = "stock")
    val stock: String,

    @ColumnInfo(name = "volume")
    val volume: Int,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)

