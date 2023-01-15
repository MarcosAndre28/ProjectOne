package com.example.projectone.Ticker

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectone.repositories.TickerRepository

class TickerViewModelFactory constructor(private val repository: TickerRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TickerViewModel::class.java)) {
            TickerViewModel(this.repository) as T
        } else {
            throw java.lang.IllegalArgumentException("ViewModel Not Found")
        }
    }
}