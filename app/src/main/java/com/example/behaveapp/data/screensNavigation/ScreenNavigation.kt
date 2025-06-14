package com.example.behaveapp.data.screensNavigation

sealed class ScreenNavigation(val ruta: String) {
    data object PresentationScreen : ScreenNavigation(ruta = "presentationScreen")
    data object LoginTutorScreen : ScreenNavigation(ruta = "loginTutorScreen")
    data object RegisterTutorScreen : ScreenNavigation(ruta = "registerTutorScreen")
    data object RegisterUserScreen : ScreenNavigation(ruta = "registerUserScreen")
    data object HomeScreen : ScreenNavigation(ruta = "homeScreen/{idUsuario}/{tipoUsuario}") {
        fun crearRuta(idUsuario: Int, tipoUsuario: Int) = "homeScreen/$idUsuario/$tipoUsuario"
    }
    data object CreateActivityScreen : ScreenNavigation(ruta = "createActivityScreen")
}