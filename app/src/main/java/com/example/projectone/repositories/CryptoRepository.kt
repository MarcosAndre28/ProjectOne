package com.example.projectone.repositories

import com.example.projectone.rest.RetrofitService

class CryptoRepository constructor(private val retrofitService: RetrofitService) {
    fun getCrypto() = retrofitService.getCrypto()
}