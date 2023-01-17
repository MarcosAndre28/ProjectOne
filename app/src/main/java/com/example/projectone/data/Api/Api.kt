package com.example.projectone.data.Api

import com.example.projectone.data.*
import com.example.projectone.data.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Api {

    @GET("api/quote/{tickers}")
    fun getTicker(): Call<List<TickerModel>>

    @GET("api/v2/crypto")
    fun getCrypto(): Call<List<CryptoModel>>

    @GET("api/v2/currency")
    fun getCurrency(): Call<List<CurrencyModel>>

    @GET("api/v2/inflation")
    suspend fun getInflation(): Response<Inflation>

    @GET("api/v2/prime-rate")
    suspend fun getSelic(): Response<SelicRate>
}