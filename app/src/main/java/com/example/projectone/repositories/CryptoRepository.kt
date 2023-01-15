package com.example.projectone.repositories

import com.example.projectone.Api.Api

class CryptoRepository constructor(private val api: Api) {
    fun getCrypto() = api.getCrypto()
}