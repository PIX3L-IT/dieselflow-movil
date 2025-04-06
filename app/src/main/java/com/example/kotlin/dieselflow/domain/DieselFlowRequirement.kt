package com.example.kotlin.dieselflow.domain

import com.example.kotlin.dieselflow.data.network.models.LoginResponse
import com.example.kotlin.dieselflow.data.repositories.MainRepository
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response

class DieselFlowRequirement {
    private val repository = MainRepository()

    suspend fun login(email: String, password: String): Response<LoginResponse> {
        return repository.login(email, password)
    }
}