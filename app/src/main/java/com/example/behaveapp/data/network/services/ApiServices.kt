package com.example.behaveapp.data.network.services

import android.util.Log
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
import com.example.behaveapp.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ApiServices @Inject constructor(private val services: ApiClient) {

    suspend fun loginService(accion: String, usuario: String, contrasena: String): LoginResponse? =
        withContext(Dispatchers.IO) {
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

    suspend fun registerService(accion: String, datos: Any): RegisterResponse? =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = when (accion) {
                    "registroTutor" -> services.registerTutor(datos as RegisterTutorRequest)
                    "registroAlumno" -> services.registerUser(datos as RegisterUserRequest)
                    else -> null
                }

                if (response == null || !response.isSuccessful) {
                    val code = response?.code() ?: 0
                    val body = response?.errorBody()?.string().orEmpty()
                    Log.e("RegisterService", "HTTP $code: $body")
                    return@runCatching null
                }

                response.body()

            }.getOrElse { e ->
                Log.e("RegisterService", "Exception: ${e.localizedMessage}", e)
                null
            }
        }

    suspend fun getTipoActividades(accion: String, idUsuario: Int): ActividadesResponse<TipoActividades>? =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = services.getTipoActividades(ActividadesRequest(accion = accion, idUsuario = idUsuario))

                if (!response.isSuccessful) {
                    val error = response.errorBody()?.string().orEmpty()
                    Log.e("getTipoActividadesService", "HTTP ${response.code()}: $error")
                    return@runCatching null
                }

                response.body()
            }.getOrElse { e ->
                Log.e("getTipoActividadesService", "Exception: ${e.localizedMessage}", e)
                null
            }
        }

    suspend fun getActividadesList(accion: String, idUsuario: Int): ActividadesResponse<Actividades>? =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = services.getActividadesList(ActividadesRequest(accion = accion, idUsuario = idUsuario))

                if (!response.isSuccessful) {
                    val error = response.errorBody()?.string().orEmpty()
                    Log.e("getTipoActividadesListService", "HTTP ${response.code()}: $error")
                    return@runCatching null
                }

                response.body()
            }.getOrElse { e ->
                Log.e("getTipoActividadesListService", "Exception: ${e.localizedMessage}", e)
                null
            }
        }

    suspend fun saveActivities(
        tipoActividad: Int,
        tutor: Int,
        titulo: String,
        puntaje: Int
    ): CreateResponse? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val request = CreateRequest(
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
                        return@withContext CreateResponse(
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
    ): CreateResponse? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val request = CreateRequest(
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
                        return@withContext CreateResponse(
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

    suspend fun deleteActividad(accion: String,idActividad: Int): CreateResponse? =
        withContext(Dispatchers.IO) {
            runCatching {
                val response = services.deleteActividad(CreateRequest(accion = accion, idActividad = idActividad))

                if (!response.isSuccessful) {
                    val error = response.errorBody()?.string().orEmpty()
                    Log.e("deleteActividadService", "HTTP ${response.code()}: $error")
                    return@runCatching null
                }

                response.body()
            }.getOrElse { e ->
                Log.e("deleteActividadService", "Exception: ${e.localizedMessage}", e)
                null
            }
        }

}