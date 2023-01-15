package com.example.projectone.repositories

import com.example.projectone.rest.RetrofitService

class InflationRepository constructor(private val retrofitService: RetrofitService) {
    fun getInflation() = retrofitService.getInflation()
}