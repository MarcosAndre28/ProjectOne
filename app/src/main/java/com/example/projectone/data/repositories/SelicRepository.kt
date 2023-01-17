package com.example.projectone.data.repositories

import com.example.projectone.data.Api.Api
import com.example.projectone.data.Api.RetrofitInstance
import com.example.projectone.data.models.SelicModel
import com.example.projectone.data.models.SelicRate
import com.example.projectone.data.viewModel.SelicViewModel
import retrofit2.Response
import retrofit2.await

class SelicRepository{
    private val api = RetrofitInstance.api

    suspend fun getSelic(): Response<SelicRate> {
        return api.getSelic()
    }
}