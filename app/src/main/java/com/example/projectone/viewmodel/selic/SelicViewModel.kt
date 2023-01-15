package com.example.projectone.Selic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectone.models.SelicModel
import com.example.projectone.repositories.SelicRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelicViewModel constructor(private val repository: SelicRepository) : ViewModel() {

    val liveList = MutableLiveData<List<SelicModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getSelic() {
        val request = repository.getSelic()
        request.enqueue(object : Callback<SelicModel> {
            override fun onResponse(call: Call<SelicModel>, response: Response<SelicModel>) {
            val response = response.body()
            }

            override fun onFailure(call: Call<SelicModel>, t: Throwable) {
            errorMessage.postValue(t.message)
            }

        })
    }
}