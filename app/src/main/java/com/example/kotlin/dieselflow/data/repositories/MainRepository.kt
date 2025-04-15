package com.example.kotlin.dieselflow.data.repositories

import com.example.kotlin.dieselflow.data.network.DieselFlowApiService
import com.example.kotlin.dieselflow.data.network.NetworkModuleDI
import com.example.kotlin.dieselflow.data.network.models.LoginRequest
import com.example.kotlin.dieselflow.data.network.models.LoginResponse
import retrofit2.Response

class MainRepository {

    private val api: DieselFlowApiService = NetworkModuleDI()

    //Para conectar nuestro ApiService y NetworkModule
    suspend fun login(user: String, password: String, type: String): Response<LoginResponse> =
        api.loginUser(LoginRequest(user, password, type))
}

