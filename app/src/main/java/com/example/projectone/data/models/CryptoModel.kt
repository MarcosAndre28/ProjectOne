package com.example.projectone.data.models

data class CryptoModel(
    val currency: String,
    val currencyRateFromUSD: Double,
    val coinName: String,
    var coin: String,
    val regularMarketChange: Double,
    val regularMarketPrice: Double,
    val regularMarketChangePercent: Double,
    val regularMarketDayLow: Double,
    val regularMarketDayHigh: Double,
    var regularMarketDayRange: String,
    val regularMarketVolume: Double,
    val marketCap: Double,
    val regularMarketTime: Long,
    val coinImageURL: String
)
