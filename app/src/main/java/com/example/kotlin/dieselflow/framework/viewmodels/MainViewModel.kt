package com.example.kotlin.dieselflow.framework.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.dieselflow.data.repositories.MainRepository
import com.example.kotlin.dieselflow.domain.DieselFlowRequirement
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response

class MainViewModel : ViewModel() {
    private val dieselFlowRequirement = DieselFlowRequirement()
    val loginSuccess = MutableLiveData<Boolean>()
    val loginError = MutableLiveData<String>()

    fun login(email: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val call = dieselFlowRequirement.login(email, password)
                val response: Response<ResponseBody> = call.execute()

                if (response.isSuccessful) {
                    loginSuccess.postValue(true)
                } else {
                    loginSuccess.postValue(false)
                    loginError.postValue("Error: ${response.code()} - ${response.message()}")
                }

            } catch (e: HttpException) {
                loginSuccess.postValue(false)
                loginError.postValue("Excepción HTTP: ${e.message()}")
            } catch (e: Exception) {
                loginSuccess.postValue(false)
                loginError.postValue("Error desconocido: ${e.localizedMessage}")
            }
        }
    }
}