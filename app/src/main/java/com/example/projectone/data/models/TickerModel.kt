package com.example.projectone.data.models

data class TickerModel(
    val stocks: List<Stock>
)
data class Stock(
    val change: Double,
    val close: Double,
    val logo: String,
    val market_cap: Double,
    val name: String,
    val sector: String,
    val stock: String,
    val volume: Int
)