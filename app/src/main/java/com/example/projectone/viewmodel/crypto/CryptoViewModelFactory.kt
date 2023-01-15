package com.example.projectone.viewmodel.crypto

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectone.repositories.CryptoRepository

class CryptoViewModelFactory constructor(private val repository: CryptoRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CryptoViewModel::class.java)) {
            CryptoViewModel(this.repository) as T
        } else {
            throw java.lang.IllegalArgumentException("ViewModel Not Found")
        }
    }
}