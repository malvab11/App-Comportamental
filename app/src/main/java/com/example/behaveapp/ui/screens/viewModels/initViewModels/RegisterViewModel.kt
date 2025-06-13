package com.example.behaveapp.ui.screens.viewModels.initViewModels

import androidx.lifecycle.ViewModel
import com.example.behaveapp.domain.registerUseCases.RegisterUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCases) : ViewModel() {

}