package com.example.projectone.viewmodel.crypto

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectone.data.models.CryptoModel
import com.example.projectone.data.repositories.CryptoRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CryptoViewModel constructor(private val repository: CryptoRepository) : ViewModel() {

    val liveList = MutableLiveData<List<CryptoModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getCrypto() {
        val request = repository.getCrypto()
        request.enqueue(object : Callback<List<CryptoModel>> {
            override fun onResponse(call: Call<List<CryptoModel>>, response: Response<List<CryptoModel>>) {
            liveList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CryptoModel>>, t: Throwable) {
            errorMessage.postValue(t.message)
            }

        })
    }
}