package br.com.denisecastro.safebus.navigation

sealed class SafeBusDestination(
    val route: String
) {
    data object Welcome : SafeBusDestination("welcome")

    data object Login : SafeBusDestination("login")

    data object Register : SafeBusDestination("register")
    data object Home : SafeBusDestination("home")
}
