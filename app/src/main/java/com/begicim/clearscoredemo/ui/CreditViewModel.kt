package com.begicim.clearscoredemo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.begicim.clearscoredemo.network.CreditApiService
import com.begicim.clearscoredemo.network.toUIModel
import com.begicim.clearscoredemo.ui.model.CreditUIModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject

enum class ApiStatus {
    LOADING,
    SUCCESS,
    ERROR
}

@HiltViewModel
class CreditViewModel @Inject constructor(
    private val creditApiService: CreditApiService,
    private val coroutineDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _creditScore = MutableLiveData<CreditUIModel>()
    val creditScore: MutableLiveData<CreditUIModel> = _creditScore

    private val _apiStatus = MutableLiveData<ApiStatus>()
    val apiStatus: LiveData<ApiStatus> = _apiStatus

    private val _progress = MutableLiveData<Int>()
    val progress: LiveData<Int> = _progress

    private val job = Job()
    private val coroutineScope = CoroutineScope(coroutineDispatcher + job)

    fun loadCreditScore() {
        getCreditScore()
    }

    private fun getCreditScore() {
        coroutineScope.launch {
            try {
                val networkResponse = creditApiService.getCreditInfo()
                _apiStatus.value = ApiStatus.LOADING

                val creditInfo = networkResponse.await()
                _creditScore.value = creditInfo.toUIModel()

                _progress.value =
                    (creditInfo.toUIModel().creditScore * 100) / creditInfo.toUIModel().creditMaxValueScore

                _apiStatus.value = ApiStatus.SUCCESS

            } catch (e: Exception) {
                e.printStackTrace()
                _apiStatus.value = ApiStatus.ERROR
            }
        }
    }
}