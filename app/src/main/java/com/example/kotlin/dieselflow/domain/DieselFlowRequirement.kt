package com.example.kotlin.dieselflow.domain

import com.example.kotlin.dieselflow.data.repositories.MainRepository
import okhttp3.ResponseBody
import retrofit2.Call

class DieselFlowRequirement {
    private val repository = MainRepository()

    suspend fun login(email: String, password: String): Call<ResponseBody> {
        return repository.login(email, password)
    }
}