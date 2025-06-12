package com.example.behaveapp.data.network

import com.example.behaveapp.data.models.LoginRequest
import com.example.behaveapp.data.models.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {

    @POST("exec")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

}