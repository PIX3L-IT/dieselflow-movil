package com.example.kotlin.dieselflow.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DieselFlowApiService {

    @GET("image/list_images")
    suspend fun getImageList(): Response<String>

}