package com.example.behaveapp.data.screensNavigation

sealed class ScreenNavigation(val ruta: String) {
    data object PresentationScreen : ScreenNavigation(ruta = "presentationScreen")
    data object LoadingScreen : ScreenNavigation(ruta = "loadingScreen")
    data object LoginTutorScreen : ScreenNavigation(ruta = "loginTutorScreen")
    data object RegisterTutorScreen : ScreenNavigation(ruta = "registerTutorScreen")
    data object RegisterUserScreen : ScreenNavigation(ruta = "registerUserScreen")
    data object HomeScreen : ScreenNavigation(ruta = "homeScreen")
    data object CreateActivityScreen : ScreenNavigation("createActivityScreen/{idActividad}") {
        fun crearRuta(idActividad: String): String = "createActivityScreen/$idActividad"
    }
}