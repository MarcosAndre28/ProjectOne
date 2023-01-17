package com.example.projectone.data.repositories

import com.example.projectone.data.Api.Api

class InflationRepository constructor(private val api: Api) {
    fun getInflation() = api.getInflation()
}