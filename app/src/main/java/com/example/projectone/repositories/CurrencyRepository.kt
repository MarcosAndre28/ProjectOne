package com.example.projectone.repositories

import com.example.projectone.rest.RetrofitService

class CurrencyRepository constructor(private val retrofitService: RetrofitService) {
    fun getCurrency() = retrofitService.getCurrency()
}