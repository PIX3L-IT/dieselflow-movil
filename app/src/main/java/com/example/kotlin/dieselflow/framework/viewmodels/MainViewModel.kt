package com.example.kotlin.dieselflow.framework.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.dieselflow.domain.DieselFlowRequirement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val dieselFlowObjectLiveData = MutableLiveData<String>()
    private val dieselFlowListRequirement = DieselFlowRequirement()

    fun getImageList(){
        viewModelScope.launch(Dispatchers.IO) {
            val result: String? = dieselFlowListRequirement()
            Log.d("Salida", result?.count().toString())
            CoroutineScope(Dispatchers.Main).launch {
                dieselFlowObjectLiveData.postValue(result)
            }
        }
    }
}