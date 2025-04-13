package com.example.kotlin.dieselflow.data.network

import com.example.kotlin.dieselflow.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModuleDI {
    private val gsonFactory:
            GsonConverterFactory = GsonConverterFactory.create()
    private val okHttpClient: OkHttpClient = OkHttpClient()

    operator fun invoke(): DieselFlowApiService {
        val baseUrl = Constants.BASE_URL

        if (!isValidUrl(baseUrl)) {
            throw IllegalArgumentException("BASE_URL no es válida: $baseUrl")
        }

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(gsonFactory)
            .build()
            .create(DieselFlowApiService::class.java)
    }

    private fun isValidUrl(url: String): Boolean {
        return url.isNotBlank() &&
                (url.startsWith("http://") ||
                        url.startsWith("https://"))
    }
}
