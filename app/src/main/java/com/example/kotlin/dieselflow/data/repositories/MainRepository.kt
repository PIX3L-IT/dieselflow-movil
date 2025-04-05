package com.example.kotlin.dieselflow.data.repositories

import android.util.Log
import com.example.kotlin.dieselflow.data.network.NetworkModuleDI
import com.example.kotlin.dieselflow.data.network.models.ImageResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class MainRepository {
    private val api = NetworkModuleDI()

    suspend fun getImageUrls(): List<String> {
        val response = api.getImageUrls()
        if (response.isSuccessful) {
            Log.d("MainRepository", "Response: ${response.body()?.images}")
            return response.body()?.images ?: emptyList()
        } else {
            Log.e("MainRepository", "Error: ${response.errorBody()?.string()}")
            throw Exception("Error al obtener imágenes")
        }
    }

}

