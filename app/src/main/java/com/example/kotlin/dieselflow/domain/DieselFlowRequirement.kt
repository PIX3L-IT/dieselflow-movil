package com.example.kotlin.dieselflow.domain

import com.example.kotlin.dieselflow.data.repositories.MainRepository
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class DieselFlowRequirement {
    private val repository = MainRepository()

    suspend operator fun invoke(): List<String> {
        return repository.getImageUrls()
    }
}
