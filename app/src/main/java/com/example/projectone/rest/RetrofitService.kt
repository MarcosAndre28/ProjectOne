package com.example.projectone.rest

import com.example.projectone.models.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET

interface RetrofitService {

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

    companion object {
        private val retrofitService: RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://brapi.dev")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance(): RetrofitService {
            return retrofitService
        }
    }
}