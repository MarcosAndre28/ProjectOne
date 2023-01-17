package com.example.projectone.data.models

data class TickerModel(
    val stock: String,
    val name: String,
    val close: Double,
    val change: Double,
    val volume: Long,
    val marketCap: Long,
    val logo: String,
    val sector: String
)
