package com.example.kotlin.dieselflow.data.repositories

import android.util.Log
import com.example.kotlin.dieselflow.data.network.NetworkModuleDI
import com.example.kotlin.dieselflow.data.network.models.Usuario
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class MainRepository {
    private val api = NetworkModuleDI()

    suspend fun getUsers(): List<Usuario> {
        val response = api.getUsers()
        return if (response.isSuccessful) {
            val users = response.body() ?: emptyList()
            Log.d("MainRepository", "Usuarios recibidos: ${users.size}")
            users
        } else {
            Log.e("MainRepository", "Error: ${response.errorBody()?.string()}")
            throw Exception("Error al obtener usuarios")
        }
    }
}
