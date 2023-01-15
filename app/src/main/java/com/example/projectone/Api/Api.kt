package com.example.projectone.Api

import com.example.projectone.models.*
import retrofit2.Call
import retrofit2.http.GET

interface Api {

    @GET("/api/quote/{tickers}")
    fun getTicker(): Call<List<TickerModel>>

    @GET("/api/v2/crypto")
    fun getCrypto(): Call<List<CryptoModel>>

    @GET("/api/v2/currency")
    fun getCurrency(): Call<List<CurrencyModel>>

    @GET("/api/v2/inflation")
    fun getInflation(): Call<InflationModel>

    @GET("/api/v2/inflation")
    fun getSelic(): Call<SelicModel>
}