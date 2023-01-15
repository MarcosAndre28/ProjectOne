package com.example.projectone.Selic

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.projectone.repositories.SelicRepository

class SelicViewModelFactory constructor(private val repository: SelicRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SelicViewModel::class.java)) {
            SelicViewModel(this.repository) as T
        } else {
            throw java.lang.IllegalArgumentException("ViewModel Not Found")
        }
    }
}