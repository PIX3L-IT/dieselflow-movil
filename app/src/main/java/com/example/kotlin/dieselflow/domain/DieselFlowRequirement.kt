package com.example.kotlin.dieselflow.domain

import com.example.kotlin.dieselflow.data.network.models.LoginResponse
import com.example.kotlin.dieselflow.data.repositories.MainRepository
import retrofit2.Response

class DieselFlowRequirement {
    private val repository = MainRepository()

    suspend fun login(user: String, password: String, type:String): Response<LoginResponse> {
        return repository.login(user, password, type)
    }
}