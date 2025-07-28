package com.example.clonetalk.feature.auth

data class LoginRequest(
    val phoneNumber: String,
    val password: String
)
