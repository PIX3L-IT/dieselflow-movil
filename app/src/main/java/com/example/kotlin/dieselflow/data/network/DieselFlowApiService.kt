package com.example.kotlin.dieselflow.data.network

import com.example.kotlin.dieselflow.data.network.models.ApiResponse
import retrofit2.http.GET

interface DieselFlowApiService {
    @GET("test/get-test")
    suspend fun getTest(): ApiResponse
}
