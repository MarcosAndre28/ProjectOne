package com.example.projectone.Inflation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectone.repositories.InflationRepository

class InflationViewModelFactory constructor(private val repository: InflationRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(InflationViewModel::class.java)) {
            InflationViewModel(this.repository) as T
        } else {
            throw java.lang.IllegalArgumentException("ViewModel Not Found")
        }
    }
}