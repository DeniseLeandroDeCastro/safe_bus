package br.com.denisecastro.safebus.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.denisecastro.safebus.feature.home.route.HomeRoute
import br.com.denisecastro.safebus.feature.home.route.LoginRoute
import br.com.denisecastro.safebus.feature.home.route.RegisterRoute
import br.com.denisecastro.safebus.feature.welcome.screen.WelcomeScreen
import br.com.denisecastro.safebus.ui.components.navigation.MainScreenScaffold
import br.com.denisecastro.safebus.ui.components.navigation.SafeBusBottomDestination

@Composable
fun SafeBusNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = SafeBusDestination.Welcome.route
    ) {

        composable(SafeBusDestination.Welcome.route) {
            WelcomeScreen(
                onLoginClick = {
                    navController.navigate(SafeBusDestination.Login.route)
                },
                onRegisterClick = {
                    navController.navigate(SafeBusDestination.Register.route)
                }
            )
        }

        composable(SafeBusDestination.Login.route) {
            LoginRoute(
                onBackClick = {
                    navController.popBackStack()
                },
                onLoginClick = {
                    navController.navigate(SafeBusDestination.Home.route) {
                        popUpTo(SafeBusDestination.Welcome.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onForgotPasswordClick = {
                    // Recuperação de senha será implementada posteriormente.
                },
                onRegisterClick = {
                    navController.navigate(SafeBusDestination.Register.route) {
                        popUpTo(SafeBusDestination.Login.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(SafeBusDestination.Register.route) {
            RegisterRoute(
                onBackClick = {
                    navController.popBackStack()
                },
                onRegisterClick = {
                    navController.navigate(SafeBusDestination.Home.route) {
                        popUpTo(SafeBusDestination.Welcome.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                },
                onLoginClick = {
                    navController.navigate(SafeBusDestination.Login.route) {
                        popUpTo(SafeBusDestination.Register.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(SafeBusDestination.Home.route) {
            MainScreenScaffold(
                selectedDestination = SafeBusBottomDestination.HOME,
                onDestinationClick = { destination ->
                    when (destination) {
                        SafeBusBottomDestination.HOME -> Unit

                        SafeBusBottomDestination.MAP -> Unit

                        SafeBusBottomDestination.CONVERSATIONS -> Unit

                        SafeBusBottomDestination.CONTACTS -> Unit

                        SafeBusBottomDestination.MORE -> Unit
                    }
                }
            ) { innerPadding ->
                HomeRoute(
                    modifier = Modifier.padding(innerPadding),
                    onNotificationsClick = {},
                    onProfileClick = {},
                    onTransportClick = {},
                    onDangerAlertClick = {},
                    onHarassmentAlertClick = {},
                    onSafetyTipsClick = {}
                )
            }
        }
    }
}
