package com.example.projectone.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.projectone.data.Api.ApiResult
import com.example.projectone.data.models.Inflation
import com.example.projectone.data.repositories.InflationRepository
import com.example.projectone.db.AppDatabase
import com.example.projectone.db.model.InflationModelDB
import com.example.projectone.utils.ApiErrorUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import timber.log.Timber


class InflationViewModel(application: Application) : AndroidViewModel(application) {

    var repository: InflationRepository
    val inflationData = MutableLiveData<Inflation>()

    init {
        val inflationDao = AppDatabase.getAppDatabase(application).InflationDao()
        repository = InflationRepository(inflationDao)
    }

    fun getInflation(): Flow<ApiResult<Inflation>> {
        return flow {
            emit(ApiResult.Loading(null, true))
            try {
                val response = repository.getInflation()
                if (response.isSuccessful) {
                    val inflation = response.body()
                    emit(ApiResult.Success(inflation))

                    inflation!!.inflation.forEach { inflation ->
                        val inflationModel = InflationModelDB(
                            id = 0,
                            date = inflation.date,
                            epochDate = inflation.epochDate,
                            value = inflation.value
                        )
                        val exists = repository.inflationExists() > 0
                        if (exists) {
                            repository.update(inflationModel)
                        } else {
                            repository.insert(inflationModel)
                        }
                    }
                    emit(ApiResult.Error(ApiErrorUtils.getErrorMessage(response.code())))
                }
            } catch (e: Exception) {
                emit(ApiResult.Error("Error getting Selic data"))
                Timber.e(e)
            }
            emit(ApiResult.Loading(null, false))
        }
    }

    fun getInflationDB(): LiveData<InflationModelDB> {
        return repository.getSelicDb()
    }
}