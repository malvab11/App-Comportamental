package com.example.behaveapp.data.network

import com.example.behaveapp.data.models.ActividadesRequest
import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.GenericResponse
import com.example.behaveapp.data.models.LoginRequest
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.models.RegisterResponse
import com.example.behaveapp.data.models.RegisterTutorRequest
import com.example.behaveapp.data.models.RegisterUserRequest
import com.example.behaveapp.data.models.ServiceActividadesRequest
import com.example.behaveapp.data.models.TipoActividadesRequest
import com.example.behaveapp.data.models.TipoActividadesResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiClient {

    //Login Tutor
    @POST("exec")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    //Register Tutor
    @POST("exec")
    suspend fun registerTutor(@Body request: RegisterTutorRequest): Response<RegisterResponse>

    //Register Usuario
    @POST("exec")
    suspend fun registerUser(@Body request: RegisterUserRequest): Response<RegisterResponse>

    //Tipo Actividad
    @POST("exec")
    suspend fun tipoActividad(@Body request: TipoActividadesRequest): Response<TipoActividadesResponse>

    //Actividades
    @POST("exec")
    suspend fun actividadesList(@Body request: ActividadesRequest): Response<ActividadesResponse>

    //Actividades
    @POST("exec")
    suspend fun saveActividad(@Body request: ServiceActividadesRequest): Response<GenericResponse>

    //Actividades
    @POST("exec")
    suspend fun editActividad(@Body request: ServiceActividadesRequest): Response<GenericResponse>


}