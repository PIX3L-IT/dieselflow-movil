package com.example.kotlin.dieselflow.data.network.models

data class Usuario(
    val _id: String,
    val idRole: String,
    val username: String,
    val lastName: String,
    val password: String,
    val registrationDate: String,
    val email: String,
    val userStatus: Boolean,
    val accessCode: String
)
