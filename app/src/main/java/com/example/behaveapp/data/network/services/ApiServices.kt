package com.example.behaveapp.data.network.services

import android.util.Log
import com.example.behaveapp.data.models.Actividades
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
import com.example.behaveapp.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ApiServices @Inject constructor(private val services: ApiClient) {

    suspend fun loginService(accion: String, usuario: String,contrasena: String): LoginResponse? = withContext(Dispatchers.IO) {
        runCatching {
            val response = services.login(
                LoginRequest(accion = accion, usuario = usuario, contrasena = contrasena)
            )

            if (!response.isSuccessful) {
                val error = response.errorBody()?.string().orEmpty()
                Log.e("LoginService", "HTTP ${response.code()}: $error")
                return@runCatching null
            }

            response.body()
        }.getOrElse { e ->
            Log.e("LoginService", "Exception: ${e.localizedMessage}", e)
            null
        }
    }

    suspend fun registerService(accion: String, datos: Any): RegisterResponse? = withContext(Dispatchers.IO){
        runCatching {
            val response = when(accion){
                "registroTutor" -> services.registerTutor(datos as RegisterTutorRequest)
                "registroAlumno" -> services.registerUser(datos as RegisterUserRequest)
                else -> null
            }

            if (response == null || !response.isSuccessful){
                val code = response?.code() ?: 0
                val body = response?.errorBody()?.string().orEmpty()
                Log.e("RegisterService", "HTTP $code: $body")
                return@runCatching null
            }

            response.body()

        }.getOrElse { e ->
            Log.e("LoginService", "Exception: ${e.localizedMessage}", e)
            null
        }
    }

    suspend fun getTipoActividades(): TipoActividadesResponse? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val request = TipoActividadesRequest()
                val response = services.tipoActividad(request)
                val body = response.body()
                val error = response.errorBody()

                if (!response.isSuccessful) {
                    Log.e(
                        "GetTipoActividad",
                        "HTTP ${response.code()}: ${error?.string()}"
                    )
                    return@withContext null
                }

                if (body?.status != "success") {
                    Log.e("GetTipoActividad", "Logic error: ${body?.message}")
                    if (body != null) {
                        return@withContext TipoActividadesResponse(
                            status = body.status,
                            message = body.message
                        )
                    }
                }

                body

            } catch (e: Exception) {
                Log.e("GetTipoActividad", "Exception: ${e.localizedMessage}", e)
                null
            }
        }

    suspend fun getActividades(idUsuario: Int): ActividadesResponse? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val request = ActividadesRequest(idUsuario = idUsuario)
                val response = services.actividadesList(request)
                val body = response.body()
                val error = response.errorBody()

                if (!response.isSuccessful) {
                    Log.e(
                        "ActividadesService",
                        "HTTP ${response.code()}: ${error?.string()}"
                    )
                    return@withContext null
                }

                if (body?.status != "success") {
                    Log.e("ActividadesService", "Logic error: ${body?.message}")
                    if (body != null) {
                        return@withContext ActividadesResponse(
                            status = body.status,
                            message = body.message
                        )
                    }
                }

                body

            } catch (e: Exception) {
                Log.e("ActividadesService", "Exception: ${e.localizedMessage}", e)
                null
            }
        }

    suspend fun saveActivities(
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int
    ): GenericResponse? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val request = ServiceActividadesRequest(
                    accion = "saveActividad",
                    datos = Actividades(
                        tipoActividad = tipoActividad,
                        tutor = tutor,
                        titulo = titulo,
                        puntaje = puntaje
                    )
                )
                val response = services.saveActividad(request)
                val body = response.body()
                val error = response.errorBody()

                if (!response.isSuccessful) {
                    Log.e(
                        "SaveActivitiesService",
                        "HTTP ${response.code()}: ${error?.string()}"
                    )
                    return@withContext null
                }

                if (body?.status != "success") {
                    Log.e("SaveActivitiesService", "Logic error: ${body?.message}")
                    if (body != null) {
                        return@withContext GenericResponse(
                            status = body.status,
                            message = body.message
                        )
                    }
                }

                body

            } catch (e: Exception) {
                Log.e("SaveActivitiesService", "Exception: ${e.localizedMessage}", e)
                null
            }
        }

    suspend fun editActivities(
        idActividad: Int,
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int,
        eliminado: Int
    ): GenericResponse? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val request = ServiceActividadesRequest(
                    accion = "editarActividad",
                    datos = Actividades(
                        idActividad = idActividad,
                        tipoActividad = tipoActividad,
                        tutor = tutor,
                        titulo = titulo,
                        puntaje = puntaje,
                        eliminado = eliminado
                    )
                )
                val response = services.saveActividad(request)
                val body = response.body()
                val error = response.errorBody()

                if (!response.isSuccessful) {
                    Log.e(
                        "EditActividadService",
                        "HTTP ${response.code()}: ${error?.string()}"
                    )
                    return@withContext null
                }

                if (body?.status != "success") {
                    Log.e("EditActividadService", "Logic error: ${body?.message}")
                    if (body != null) {
                        return@withContext GenericResponse(
                            status = body.status,
                            message = body.message
                        )
                    }
                }

                body

            } catch (e: Exception) {
                Log.e("EditActividadService", "Exception: ${e.localizedMessage}", e)
                null
            }
        }

}