package com.example.projectone.repositories

import com.example.projectone.Api.Api

class InflationRepository constructor(private val api: Api) {
    fun getInflation() = api.getInflation()
}