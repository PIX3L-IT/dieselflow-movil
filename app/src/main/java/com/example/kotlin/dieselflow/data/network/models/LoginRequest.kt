package com.example.kotlin.dieselflow.data.network.models

data class LoginRequest(
    val user: String,
    val password: String,
    val type: String
)
