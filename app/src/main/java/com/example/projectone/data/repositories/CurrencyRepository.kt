package com.example.projectone.data.repositories

import com.example.projectone.data.Api.Api

class CurrencyRepository constructor(private val api: Api) {
    fun getCurrency() = api.getCurrency()
}