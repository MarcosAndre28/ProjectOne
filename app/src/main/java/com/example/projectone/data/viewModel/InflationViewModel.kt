package com.example.projectone.data.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.projectone.data.models.Inflation
import com.example.projectone.data.models.InflationModel
import com.example.projectone.data.models.SelicRate
import com.example.projectone.data.repositories.InflationRepository
import com.example.projectone.data.repositories.SelicRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class InflationViewModel: ViewModel() {

    private val repository = InflationRepository()
    val inflationData = MutableLiveData<Inflation>()

    suspend fun getInflation() {
        try {
            val response = repository.getInflation()
            Log.d("API_RESPONSE", response.toString())

            withContext(Dispatchers.Main){
                if (response.isSuccessful){
                    inflationData.value = response.body()
                    Log.d("SELIC_DATA", inflationData.value.toString())
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