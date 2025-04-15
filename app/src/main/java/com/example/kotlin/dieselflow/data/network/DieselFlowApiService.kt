package com.example.kotlin.dieselflow.data.network

import com.example.kotlin.dieselflow.data.network.models.LoginRequest
import com.example.kotlin.dieselflow.data.network.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface DieselFlowApiService {

    @POST("/login")
    suspend fun loginUser(@Body request: LoginRequest): Response<LoginResponse>
}

