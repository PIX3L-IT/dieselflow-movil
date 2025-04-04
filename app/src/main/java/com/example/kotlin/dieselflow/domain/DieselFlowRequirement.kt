package com.example.kotlin.dieselflow.domain

import com.example.kotlin.dieselflow.data.network.models.ApiResponse
import com.example.kotlin.dieselflow.data.repositories.MainRepository

class DieselFlowRequirement {
    private val repository = MainRepository()

    suspend operator fun invoke(): ApiResponse? = repository.getTest()
}
