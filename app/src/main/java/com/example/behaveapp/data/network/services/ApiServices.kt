package com.example.behaveapp.data.network.services

import android.util.Log
import com.example.behaveapp.data.models.LoginRequest
import com.example.behaveapp.data.models.LoginResponse
import com.example.behaveapp.data.network.ApiClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ApiServices @Inject constructor(private val services: ApiClient) {

    suspend fun loginService(usuario: String, contrasena: String): LoginResponse? =
        withContext(Dispatchers.IO) {
            return@withContext try {
                val request = LoginRequest(usuario = usuario, contrasena = contrasena)
                val response = services.login(request)
                val body = response.body()
                val error = response.errorBody()

                if (!response.isSuccessful) {
                    Log.e(
                        "LoginService",
                        "HTTP ${response.code()}: ${error?.string()}"
                    )
                    return@withContext null
                }

                if (body?.status != "success"){
                    Log.e("LoginService", "Logic error: ${body?.message}")
                    if (body != null) {
                        return@withContext LoginResponse(status = body.status, message = body.message)
                    }
                }

                body

            } catch (e: Exception) {
                Log.e("LoginService", "Exception: ${e.localizedMessage}", e)
                null
            }
        }

}