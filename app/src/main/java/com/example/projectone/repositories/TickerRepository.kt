package com.example.projectone.repositories

import com.example.projectone.Api.Api

class TickerRepository constructor(private val api: Api) {
    fun getTicker() = api.getTicker()
}