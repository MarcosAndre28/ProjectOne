package com.example.projectone.repositories

import com.example.projectone.rest.RetrofitService

class TickerRepository constructor(private val retrofitService: RetrofitService) {
    fun getTicker() = retrofitService.getTicker()
}