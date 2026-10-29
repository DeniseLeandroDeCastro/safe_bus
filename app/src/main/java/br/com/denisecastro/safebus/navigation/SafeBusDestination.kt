package br.com.denisecastro.safebus.navigation

sealed class SafeBusDestination(
    val route: String
) {
    data object Welcome : SafeBusDestination("welcome")
    data object Login : SafeBusDestination("login")
    data object Register : SafeBusDestination("register")
    data object Home : SafeBusDestination("home")
    data object Profile : SafeBusDestination("profile")
    data object DangerAlert : SafeBusDestination("danger_alert")
    data object AlertLocation : SafeBusDestination("alert_location")
    data object AlertReview : SafeBusDestination("alert_review")
}