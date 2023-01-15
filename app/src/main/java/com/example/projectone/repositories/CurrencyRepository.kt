package com.example.projectone.repositories

import com.example.projectone.Api.Api

class CurrencyRepository constructor(private val api: Api) {
    fun getCurrency() = api.getCurrency()
}