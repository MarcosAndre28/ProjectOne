package com.example.projectone.Ticker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectone.data.models.TickerModel
import com.example.projectone.data.repositories.TickerRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class TickerViewModel constructor(private val repository: TickerRepository) : ViewModel() {

    val liveList = MutableLiveData<List<TickerModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getTicker() {
        val request = repository.getTicker()
        request.enqueue(object : Callback<List<TickerModel>> {
            override fun onResponse(
                call: Call<List<TickerModel>>,
                response: Response<List<TickerModel>>
            ) {
                liveList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<TickerModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }

        })
    }
}