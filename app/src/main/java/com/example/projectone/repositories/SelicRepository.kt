package com.example.projectone.repositories

import com.example.projectone.rest.RetrofitService

class SelicRepository constructor(private val retrofitService: RetrofitService) {
    fun getSelic() = retrofitService.getSelic()
}