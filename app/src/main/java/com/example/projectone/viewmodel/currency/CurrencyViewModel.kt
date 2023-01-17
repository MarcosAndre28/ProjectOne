package com.example.projectone.Currency

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectone.data.models.CurrencyModel
import com.example.projectone.data.repositories.CurrencyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CurrencyViewModel constructor(private val repository: CurrencyRepository) : ViewModel() {

    val liveList = MutableLiveData<List<CurrencyModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getCurrency() {
        val request = repository.getCurrency()
        request.enqueue(object : Callback<List<CurrencyModel>> {
            override fun onResponse(call: Call<List<CurrencyModel>>, response: Response<List<CurrencyModel>>) {
            liveList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CurrencyModel>>, t: Throwable) {
            errorMessage.postValue(t.message)
            }

        })
    }
}