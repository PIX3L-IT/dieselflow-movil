package com.example.kotlin.dieselflow.data.repositories

import com.example.kotlin.dieselflow.data.network.NetworkModuleDI
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

class UploadRepository {
    suspend fun uploadImageToS3(file: MultipartBody.Part): Response<ResponseBody> {
        return NetworkModuleDI().uploadPhotoToS3(file)
    }
}