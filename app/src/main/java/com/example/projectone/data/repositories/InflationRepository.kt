package com.example.projectone.data.repositories

import com.example.projectone.data.Api.Api
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.Inflation
import com.example.projectone.data.models.SelicRate
import retrofit2.Response

class InflationRepository{
    private val api = RetrofitInstance.api

    suspend fun getInflation(): Response<Inflation> {
        return api.getInflation()
    }
}