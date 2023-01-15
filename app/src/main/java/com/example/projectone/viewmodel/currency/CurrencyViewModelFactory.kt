package com.example.projectone.Currency

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectone.repositories.CurrencyRepository

class CurrencyViewModelFactory constructor(private val repository: CurrencyRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(CurrencyViewModel::class.java)) {
            CurrencyViewModel(this.repository) as T
        } else {
            throw java.lang.IllegalArgumentException("ViewModel Not Found")
        }
    }
}