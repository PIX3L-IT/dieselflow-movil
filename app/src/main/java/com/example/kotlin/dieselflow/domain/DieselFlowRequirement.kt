package com.example.kotlin.dieselflow.domain

import com.example.kotlin.dieselflow.data.repositories.MainRepository

class DieselFlowRequirement {
    private val repository = MainRepository()

    suspend operator fun invoke(

    ): String? = repository.getImageList()
}