package com.example.projectone.repositories

import com.example.projectone.Api.Api

class SelicRepository constructor(private val api: Api) {
    fun getSelic() = api.getSelic()
}