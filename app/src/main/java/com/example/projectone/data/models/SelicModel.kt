package com.example.projectone.data.models

import com.google.gson.annotations.SerializedName

data class SelicRate(
    @SerializedName("prime-rate")
    val primeRate: List<SelicModel>
)

data class SelicModel(
    @SerializedName("date")
    val date: String,
    @SerializedName("epochDate")
    val epochDate: Long,
    @SerializedName("value")
    val value: String
)