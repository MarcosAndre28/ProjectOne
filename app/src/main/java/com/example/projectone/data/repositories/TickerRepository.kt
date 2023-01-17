package com.example.projectone.data.repositories

import com.example.projectone.data.Api.Api

class TickerRepository constructor(private val api: Api) {
    fun getTicker() = api.getTicker()
}