package com.example.projectone.data.viewModel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.projectone.data.Api.ApiError
import com.example.projectone.data.models.SelicRate
import com.example.projectone.data.repositories.SelicRepository
import com.example.projectone.db.AppDatabase
import com.example.projectone.db.model.Selic
import com.example.projectone.data.Api.ApiResult
import com.example.projectone.utils.ApiErrorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber
import java.nio.file.Files
import java.nio.file.Files.exists


class SelicViewModel(application: Application): AndroidViewModel(application) {

    var repository : SelicRepository
    private val viewModelScope = CoroutineScope(Dispatchers.Main)

    init {
        val selicDao = AppDatabase.getAppDatabase(application).SelicDao()
        repository =  SelicRepository(selicDao)
    }

    // API
    fun getSelicData(): Flow<ApiResult<SelicRate>> {
        return flow {
            emit(ApiResult.Loading(null, true))
            try {
                val response = repository.getSelic()
                if (response.isSuccessful) {
                    val selicRate = response.body()
                    emit(ApiResult.Success(selicRate))

                    selicRate!!.primeRate.forEach{rate ->
                        val selic = Selic(id = 0, epochDate =rate.epochDate, date = rate.date, value=  rate.value)
                        val exists = repository.selicExists() > 0
                        if(exists){
                            repository.update(selic)
                        }
                        else{
                            repository.insert(selic)
                        }
                    }
                } else {
                    emit(ApiResult.Error(ApiErrorUtils.getErrorMessage(response.code())))
                }
            } catch (e: Exception) {
                emit(ApiResult.Error("Error getting Selic data"))
                Timber.e(e)
            }
            emit(ApiResult.Loading(null, false))
        }
    }

    // DB
    fun update(selic: Selic){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(selic)
        }
    }

    fun insert(selic: Selic){
        viewModelScope.launch(Dispatchers.IO){
            repository.insert(selic)
        }
    }

    fun delete(selic: Selic){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(selic)
        }
    }

    fun getSelicDB(): LiveData<Selic>{
        return repository.getSelicDb()
    }
}