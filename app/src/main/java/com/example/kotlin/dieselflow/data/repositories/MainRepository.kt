package com.example.kotlin.dieselflow.data.repositories

import com.example.kotlin.dieselflow.data.network.DieselFlowApiService
import com.example.kotlin.dieselflow.data.network.NetworkModuleDI
import com.example.kotlin.dieselflow.data.network.models.ApiResponse

class MainRepository {
    private lateinit var api: DieselFlowApiService

    suspend fun getTest(): ApiResponse? {
        api = NetworkModuleDI()
        // Log.d("MainRepository", api.getTest().body().toString())
        return try {
            api.getTest()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            null
        }
    }
}
