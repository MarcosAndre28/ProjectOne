package com.example.projectone.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.projectone.utils.Constants.Companion.CRYPTO

@Entity(tableName = CRYPTO)
data class CryptoModelDB(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @ColumnInfo(name = "currency")
    val currency: String,

    @ColumnInfo(name = "currencyRateFromUSD")
    val currencyRateFromUSD: Double,

    @ColumnInfo(name = "coinName")
    val coinName: String,

    @ColumnInfo(name = "coin")
    val coin: String,

    @ColumnInfo(name = "regularMarketChange")
    val regularMarketChange: Double,

    @ColumnInfo(name = "regularMarketPrice")
    val regularMarketPrice: Double,

    @ColumnInfo(name = "regularMarketChangePercent")
    val regularMarketChangePercent: Double,

    @ColumnInfo(name = "regularMarketDayLow")
    val regularMarketDayLow: Double,

    @ColumnInfo(name = "regularMarketDayHigh")
    val regularMarketDayHigh: Double,

    @ColumnInfo(name = "regularMarketDayRange")
    val regularMarketDayRange: String,

    @ColumnInfo(name = "regularMarketVolume")
    val regularMarketVolume: Double,

    @ColumnInfo(name = "marketCap")
    val marketCap: Double,

    @ColumnInfo(name = "regularMarketTime")
    val regularMarketTime: Long,

    @ColumnInfo(name = "coinImageURL")
    val coinImageURL: String
)
