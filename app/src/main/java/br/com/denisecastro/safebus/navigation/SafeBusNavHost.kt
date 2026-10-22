package br.com.denisecastro.safebus.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.denisecastro.safebus.feature.login.screen.LoginRoute
import br.com.denisecastro.safebus.feature.register.screen.RegisterRoute
import br.com.denisecastro.safebus.feature.welcome.screen.WelcomeScreen

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
                    navController.navigate(
                        SafeBusDestination.Login.route
                    )
                },
                onRegisterClick = {
                    navController.navigate(
                        SafeBusDestination.Register.route
                    )
                }
            )
        }

        composable(SafeBusDestination.Login.route) {
            LoginRoute(
                onBackClick = {
                    navController.popBackStack()
                },
                onLoginClick = {
                    // A autenticação será implementada posteriormente.
                },
                onForgotPasswordClick = {
                    // A recuperação de senha será implementada posteriormente.
                },
                onRegisterClick = {
                    navController.navigate(
                        SafeBusDestination.Register.route
                    ) {
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
                    // O cadastro será integrado posteriormente.
                },
                onLoginClick = {
                    navController.navigate(
                        SafeBusDestination.Login.route
                    ) {
                        popUpTo(SafeBusDestination.Register.route) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }
    }
}
