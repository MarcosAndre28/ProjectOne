package com.example.projectone.data.models

data class Inflation(
    val inflation: List<InflationModel>
)
data class InflationModel(
    val date: String,
    val value: String,
    val epochDate: Long
)