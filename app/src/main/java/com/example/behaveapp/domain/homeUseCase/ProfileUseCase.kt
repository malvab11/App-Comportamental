package com.example.behaveapp.domain.homeUseCase

import com.example.behaveapp.data.repository.RoomRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val roomRepository: RoomRepository
)  {
    suspend fun cerrarSesion() = roomRepository.clearAll()
}