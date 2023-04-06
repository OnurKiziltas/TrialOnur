package com.example.trialonur.features.navigation

sealed class NavScreen(val route: String) {
    object Home : NavScreen("home")
    object SecondScreen : NavScreen("secondScreen")
}
