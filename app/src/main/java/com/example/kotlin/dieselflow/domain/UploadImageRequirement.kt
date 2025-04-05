package com.example.kotlin.dieselflow.domain

import com.example.kotlin.dieselflow.data.repositories.UploadRepository
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class UploadImageRequirement {
    private val repository = UploadRepository()

    suspend operator fun invoke(file: MultipartBody.Part): Response<ResponseBody> {
        return repository.uploadImageToS3(file)
    }
}