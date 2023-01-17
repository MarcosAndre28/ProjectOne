package com.example.projectone.data.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectone.data.models.SelicModel
import com.example.projectone.data.models.SelicRate
import com.example.projectone.data.repositories.InflationRepository
import com.example.projectone.data.repositories.SelicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SelicViewModel: ViewModel() {

    private val repository = SelicRepository()
    val selicData = MutableLiveData<SelicRate>()

    suspend fun getSelic() {
        try {
            val response = repository.getSelic()
            Log.d("API_RESPONSE", response.toString())

            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    selicData.value = response.body()
                    Log.d("SELIC_DATA", selicData.value.toString())
                }
                else{
                    throw  Exception("Error getting Selic data")
                }
            }
        }catch (e: Exception){
            e.stackTrace
        }
    }
}