package com.example.clonetalk.core.network

import com.example.clonetalk.feature.auth.LoginRequest
import com.example.clonetalk.feature.auth.LoginResponse
import com.example.clonetalk.feature.auth.SignupRequest
import com.example.clonetalk.feature.auth.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("member/signup")
    suspend fun signup(@Body request: SignupRequest): Response<SignupResponse>
}