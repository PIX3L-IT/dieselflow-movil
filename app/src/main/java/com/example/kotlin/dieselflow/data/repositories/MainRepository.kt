package com.example.kotlin.dieselflow.data.repositories

import android.util.Log
import com.example.kotlin.dieselflow.data.network.DieselFlowApiService
import com.example.kotlin.dieselflow.data.network.NetworkModuleDI
import retrofit2.Response

class MainRepository {

    private lateinit var api: DieselFlowApiService

    suspend fun getImageList(): String? {
        api = NetworkModuleDI()  // Initialize API service
        Log.d("MainRepository", "HOLAAA")
        return try {
            val response: Response<String> = api.getImageList()  // Get the HTML response from the API
            Log.d("MainRepository", "ERROR")
            if (response.isSuccessful) {
                // Log the successful HTML response
                Log.d("MainRepository", "HTML Response: ${response.body()}")
                return response.body()  // Return the HTML body as a string
            } else {
                // Log error response
                Log.e("MainRepository", "Error: ${response.errorBody()?.string()}")
                null
            }

        } catch (e: Exception) {
            // Handle any exceptions (network issues, etc.)
            e.printStackTrace()
            null

        }
    }
}
