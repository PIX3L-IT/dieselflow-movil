package com.example.kotlin.dieselflow.framework.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import android.util.Log
import com.example.kotlin.dieselflow.domain.DieselFlowRequirement
import java.io.IOException
import com.google.gson.JsonParseException

class MainViewModel : ViewModel() {
    private val dieselFlowRequirement = DieselFlowRequirement()
    val loginSuccess = MutableLiveData<Boolean>()
    val loginError = MutableLiveData<String>()

    private var accessToken: String? = null
    private var refreshToken: String? = null

    fun login(user: String, password: String, type: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = dieselFlowRequirement.login(user, password, type)

                if (response.isSuccessful) {
                    val contentType = response.headers()["Content-Type"]

                    if (contentType?.contains("application/json") == true) {
                        val loginResponse = response.body()
                        accessToken = loginResponse?.accessToken
                        refreshToken = loginResponse?.refreshToken
                        loginSuccess.postValue(true)
                    } else {
                        // Es HTML, por lo tanto es un error renderizado del servidor
                        loginSuccess.postValue(false)
                        loginError.postValue("Credenciales inválidas")
                    }

                } else {
                    // Si no es exitoso, intenta leer el cuerpo de error (HTML o texto)
                    val errorBody = response.errorBody()?.string()
                    Log.e("Login", "Error body: $errorBody")
                    loginSuccess.postValue(false)

                    if (errorBody?.contains("Credenciales inválidas", ignoreCase = true) == true) {
                        loginError.postValue("Credenciales inválidas")
                    } else {
                        loginError.postValue("Error: ${response.code()} - ${response.message()}")
                    }
                }

            } catch (e: HttpException) {
                Log.e("Login", "Respuesta HTTP no exitosa: ${e.code()} - ${e.message()}")
                loginSuccess.postValue(false)
                loginError.postValue("Error en la solicitud, por favor intenta de nuevo")
            } catch (e: IOException) {
                Log.e("Login", "Fallo de red: ${e.localizedMessage}")
                loginSuccess.postValue(false)
                loginError.postValue("Error en la solicitud, por favor intenta de nuevo")
            } catch (e: JsonParseException) {
                Log.e("Login", "Error al parsear JSON: ${e.localizedMessage}")
                loginSuccess.postValue(false)
                loginError.postValue("Error en la solicitud, por favor intenta de nuevo")
            } catch (e: Exception) {
                Log.e("Login", "Error inesperado: ${e.localizedMessage}")
                loginSuccess.postValue(false)
                loginError.postValue("Error en la solicitud, por favor intenta de nuevo")
            }
        }
    }
}
