package com.example.projectone.data.repositories

import com.example.projectone.data.Api.Api

class CryptoRepository constructor(private val api: Api) {
    fun getCrypto() = api.getCrypto()
}