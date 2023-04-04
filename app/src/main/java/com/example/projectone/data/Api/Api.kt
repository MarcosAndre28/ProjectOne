package com.example.projectone.data.Api

import com.example.projectone.data.*
import com.example.projectone.data.models.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("api/quote/list")
    suspend fun getTicker(): Response<TickerModel>

    @GET("/api/v2/crypto")
    suspend fun getCrypto(@Query("coin") coin: String, @Query("BRL") currency: String): Response<CryptoModel>
    @GET("api/v2/crypto/available")
    suspend fun getCryptoAvailable(): Response<CryptoAvailableModel>

    @GET("api/v2/currency")
    fun getCurrency(): Call<List<CurrencyModel>>

    @GET("api/v2/inflation")
    suspend fun getInflation(): Response<Inflation>

    @GET("api/v2/prime-rate")
    suspend fun getSelic(): Response<SelicRate>
}