package com.example.kotlin.dieselflow.data.network

import com.example.kotlin.dieselflow.data.network.models.ImageResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface DieselFlowApiService {
    @GET("image/list_json")
    suspend fun getImageUrls(): Response<ImageResponse>

    @Multipart
    @POST("upload/s3_multer")
    suspend fun uploadPhotoToS3(
        @Part file: MultipartBody.Part
    ): Response<ResponseBody>
}
