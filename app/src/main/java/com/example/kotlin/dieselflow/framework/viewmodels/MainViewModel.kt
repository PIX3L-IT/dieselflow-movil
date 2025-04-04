package com.example.kotlin.dieselflow.framework.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.dieselflow.data.network.models.ApiResponse
import com.example.kotlin.dieselflow.domain.DieselFlowRequirement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val dieselFlowObjectLiveData = MutableLiveData<ApiResponse>()
    private val dieselFlowListRequirement = DieselFlowRequirement()

    fun getTest() {
        viewModelScope.launch(Dispatchers.IO) {
            val result: ApiResponse? = dieselFlowListRequirement()
            Log.d("Salida", result.toString())
            CoroutineScope(Dispatchers.Main).launch {
                dieselFlowObjectLiveData.postValue(result)
            }
        }
    }
}
