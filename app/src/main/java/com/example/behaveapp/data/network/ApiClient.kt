package com.example.behaveapp.data.network

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ActividadesRequest
import com.example.behaveapp.data.models.ActividadesResponse
import com.example.behaveapp.data.models.CreateRequest
import com.example.behaveapp.data.models.CreateResponse
import com.example.behaveapp.data.models.LoginRequest
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.models.RegisterResponse
import com.example.behaveapp.data.models.RegisterTutorRequest
import com.example.behaveapp.data.models.RegisterUserRequest
import com.example.behaveapp.data.models.TipoActividades
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
    suspend fun getTipoActividades(@Body request: ActividadesRequest): Response<ActividadesResponse<TipoActividades>>

    //Actividades List
    @POST("exec")
    suspend fun getActividadesList(@Body request: ActividadesRequest): Response<ActividadesResponse<Actividades>>

    //Crear Actividad
    @POST("exec")
    suspend fun saveActividad(@Body request: CreateRequest): Response<CreateResponse>

    //Editar Actividad
    @POST("exec")
    suspend fun editActividad(@Body request: CreateRequest): Response<CreateResponse>

    //Eliminar Actividad
    @POST("exec")
    suspend fun deleteActividad(@Body request: CreateRequest): Response<CreateResponse>


}