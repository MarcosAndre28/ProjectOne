package com.example.projectone.Inflation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectone.models.InflationModel
import com.example.projectone.repositories.InflationRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InflationViewModel constructor(private val repository: InflationRepository) : ViewModel() {


    val errorMessage = MutableLiveData<String>()

    fun getInflation() {
        val request = repository.getInflation()
        request.enqueue(object : Callback<InflationModel> {
            override fun onResponse(call: Call<InflationModel>, response: Response<InflationModel>) {
                val response = response.body()
//                val primeRate = response?.primeRate?.first()
            }

            override fun onFailure(call: Call<InflationModel>, t: Throwable) {
            errorMessage.postValue(t.message)
            }

        })
    }
}