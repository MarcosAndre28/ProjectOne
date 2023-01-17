package com.example.projectone.data.models

data class CurrencyModel(
    val fromCurrency: String,
    val toCurrency: String,
    val name: String,
    val high: String,
    val low: String,
    val bidVariation: String,
    val percentageChange: String,
    val bidPrice: String,
    val askPrice: String,
    val updatedAtTimestamp: String,
    val updatedAtDate: String
)
