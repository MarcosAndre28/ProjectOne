package com.example.projectone.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.projectone.data.models.SelicRate
import com.example.projectone.data.repositories.SelicRepository
import com.example.projectone.db.AppDatabase
import com.example.projectone.db.model.SelicModelDB
import com.example.projectone.data.Api.ApiResult
import com.example.projectone.utils.ApiErrorUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import timber.log.Timber


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
                        val selicModelDB = SelicModelDB(id = 0, epochDate =rate.epochDate, date = rate.date, value=  rate.value)
                        val exists = repository.selicExists() > 0
                        if(exists){
                            repository.update(selicModelDB)
                        }
                        else{
                            repository.insert(selicModelDB)
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
    fun delete(selicModelDB: SelicModelDB){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(selicModelDB)
        }
    }

    fun getSelicDB(): LiveData<SelicModelDB>{
        return repository.getSelicDb()
    }
}