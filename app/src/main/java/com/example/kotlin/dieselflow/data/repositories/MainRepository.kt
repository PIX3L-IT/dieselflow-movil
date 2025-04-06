package com.example.kotlin.dieselflow.data.repositories

import android.util.Log
import com.example.kotlin.dieselflow.data.network.DieselFlowApiService
import com.example.kotlin.dieselflow.data.network.NetworkModuleDI
import com.example.kotlin.dieselflow.data.network.models.LoginRequest
import com.example.kotlin.dieselflow.data.network.models.LoginResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class MainRepository {

    private val api: DieselFlowApiService = NetworkModuleDI()

    suspend fun login(email: String, password: String): Response<LoginResponse> {
        return api.loginUser(LoginRequest(email, password))
    }

}

