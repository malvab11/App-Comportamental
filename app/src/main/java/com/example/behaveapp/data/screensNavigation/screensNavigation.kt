package com.example.behaveapp.data.screensNavigation

sealed class screensNavigation(val ruta: String) {
    data object PresentationScreen: screensNavigation(ruta = "presentationScreen")
    data object LoginTutorScreen: screensNavigation(ruta = "loginTutorScreen")
    data object RegisterTutorScreen: screensNavigation(ruta = "registerTutorScreen")
    data object LoginUserScreen: screensNavigation(ruta = "loginUserScreen")
    data object LoadingScreen: screensNavigation(ruta = "loadingScreen")
    data object HomeScreen: screensNavigation(ruta = "homeScreen")
}