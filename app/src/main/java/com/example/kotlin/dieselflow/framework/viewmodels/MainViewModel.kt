package com.example.kotlin.dieselflow.framework.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlin.dieselflow.domain.DieselFlowRequirement
import com.example.kotlin.dieselflow.data.network.models.LoginResponse
import kotlinx.coroutines.Dispatchers
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
            Log.d("Login", "Intentando hacer login con email: $email")

            try {
                // Realiza la llamada al repositorio para hacer el login
                val response = dieselFlowRequirement.login(email, password)

                if (response.isSuccessful) {
                    // Si la respuesta es exitosa, obtiene los tokens
                    val loginResponse = response.body()
                    val accessToken = loginResponse?.accessToken
                    val refreshToken = loginResponse?.refreshToken

                    // Muestra los tokens en Logcat
                    if (accessToken != null && refreshToken != null) {
                        Log.d("Login", "Access Token: $accessToken")
                        Log.d("Login", "Refresh Token: $refreshToken")
                    } else {
                        Log.e("Login", "Tokens no disponibles en la respuesta.")
                    }

                    loginSuccess.postValue(true)
                } else {
                    Log.e("Login", "Error al hacer login: ${response.code()} - ${response.message()}")
                    loginSuccess.postValue(false)
                    loginError.postValue("Error: ${response.code()} - ${response.message()}")
                }

            } catch (e: HttpException) {
                Log.e("Login", "Excepción HTTP: ${e.message()}")
                loginSuccess.postValue(false)
                loginError.postValue("Excepción HTTP: ${e.message()}")
            } catch (e: Exception) {
                Log.e("Login", "Error desconocido: ${e.localizedMessage}")
                loginSuccess.postValue(false)
                loginError.postValue("Error desconocido: ${e.localizedMessage}")
            }
        }
    }
}
