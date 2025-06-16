package com.example.behaveapp.data.network

import com.example.behaveapp.data.models.Actividades
import com.example.behaveapp.data.models.ActividadesRequest
import com.example.behaveapp.data.models.ApiResponse
import com.example.behaveapp.data.models.CreateRequest
import com.example.behaveapp.data.models.GenericResponse
import com.example.behaveapp.data.models.LoginRequest
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.models.RegisterTutorRequest
import com.example.behaveapp.data.models.RegisterUserRequest
import com.example.behaveapp.data.models.ReporteData
import com.example.behaveapp.data.models.ReporteRequest
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
    suspend fun registerTutor(@Body request: RegisterTutorRequest): Response<LoginResponse>

    //Register Usuario
    @POST("exec")
    suspend fun registerUser(@Body request: RegisterUserRequest): Response<LoginResponse>

    //Tipo Actividad
    @POST("exec")
    suspend fun getTipoActividades(@Body request: ActividadesRequest): Response<ApiResponse<List<TipoActividades>>>

    //Actividades List
    @POST("exec")
    suspend fun getActividadesList(@Body request: ActividadesRequest): Response<ApiResponse<List<Actividades>>>

    //Crear Actividad
    @POST("exec")
    suspend fun saveActividad(@Body request: CreateRequest): Response<GenericResponse>

    //Editar Actividad
    @POST("exec")
    suspend fun editActividad(@Body request: CreateRequest): Response<GenericResponse>

    //Eliminar Actividad
    @POST("exec")
    suspend fun deleteActividad(@Body request: CreateRequest): Response<GenericResponse>

    //Prueba
    @POST("exec")
    suspend fun getInfoReporte(@Body request: ReporteRequest): Response<ApiResponse<ReporteData>>

}