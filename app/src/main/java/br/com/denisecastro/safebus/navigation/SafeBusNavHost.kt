package br.com.denisecastro.safebus.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.denisecastro.safebus.feature.home.route.HomeRoute
import br.com.denisecastro.safebus.feature.login.route.LoginRoute
import br.com.denisecastro.safebus.feature.profile.route.ProfileRoute
import br.com.denisecastro.safebus.feature.register.route.RegisterRoute
import br.com.denisecastro.safebus.feature.welcome.screen.WelcomeScreen
import br.com.denisecastro.safebus.ui.components.navigation.MainScreenScaffold
import br.com.denisecastro.safebus.ui.components.navigation.SafeBusBottomDestination
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import br.com.denisecastro.safebus.feature.dangeralert.route.DangerAlertRoute
import br.com.denisecastro.safebus.feature.profile.components.dialog.LogoutConfirmationDialog
import br.com.denisecastro.safebus.feature.dangeralert.route.AlertLocationRoute
import androidx.lifecycle.viewmodel.compose.viewModel
import br.com.denisecastro.safebus.feature.dangeralert.route.AlertReviewRoute
import br.com.denisecastro.safebus.feature.dangeralert.viewmodel.EmergencyAlertViewModel


@Composable
fun SafeBusNavHost(
    navController: NavHostController = rememberNavController()
) {
    val startDestination = remember {
        if (FirebaseAuth.getInstance().currentUser != null) {
            SafeBusDestination.Home.route
        } else {
            SafeBusDestination.Welcome.route
        }
    }

    var showLogoutDialog by remember {
        mutableStateOf(false)
    }

    val emergencyAlertViewModel: EmergencyAlertViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = startDestination
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
                        SafeBusBottomDestination.PROFILE -> {
                            navController.navigate(
                                SafeBusDestination.Profile.route
                            ) {
                                launchSingleTop = true
                            }
                        }
                        SafeBusBottomDestination.MORE -> Unit
                    }
                }
            ) { innerPadding ->
                HomeRoute(
                    modifier = Modifier.padding(innerPadding),
                    onNotificationsClick = {},
                    onProfileClick = {
                        navController.navigate(
                            SafeBusDestination.Profile.route
                        ) {
                            launchSingleTop = true
                        }
                    },
                    onTransportClick = {},

                    onDangerAlertClick = {
                        navController.navigate(SafeBusDestination.DangerAlert.route) {
                            launchSingleTop = true
                        }
                    },
                    onHarassmentAlertClick = {},
                    onLogoutClick = {
                        showLogoutDialog = true
                    },
                    onSafetyTipsClick = {}
                )
            }
        }

        composable(SafeBusDestination.DangerAlert.route) {
            DangerAlertRoute(
                onBackClick = {
                    navController.popBackStack()
                },
                onConfirmClick = {
                    navController.navigate(SafeBusDestination.AlertLocation.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(SafeBusDestination.AlertLocation.route) {
            AlertLocationRoute(
                onBackClick = {
                    navController.popBackStack()
                },
                onContinueClick = { location ->
                    emergencyAlertViewModel.setLocation(location)

                    navController.navigate(SafeBusDestination.AlertReview.route) {
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(SafeBusDestination.AlertReview.route) {
            AlertReviewRoute(
                viewModel = emergencyAlertViewModel,
                onBackClick = {
                    navController.popBackStack()
                },
                onConfirmClick = {
                    // Será implementado após configurar o Repository.
                }
            )
        }

        composable(SafeBusDestination.Profile.route) {
            MainScreenScaffold(
                selectedDestination = SafeBusBottomDestination.PROFILE,
                onDestinationClick = { destination ->
                    when (destination) {
                        SafeBusBottomDestination.HOME -> {
                            navController.popBackStack(
                                SafeBusDestination.Home.route,
                                false
                            )
                        }
                        SafeBusBottomDestination.MAP -> Unit
                        SafeBusBottomDestination.PROFILE -> Unit
                        SafeBusBottomDestination.MORE -> Unit
                    }
                }
            ) { innerPadding ->
                ProfileRoute(
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }

    if (showLogoutDialog) {
        LogoutConfirmationDialog(
            onDismiss = {
                showLogoutDialog = false
            },
            onConfirm = {
                showLogoutDialog = false

                FirebaseAuth.getInstance().signOut()

                navController.navigate(
                    SafeBusDestination.Welcome.route
                ) {
                    popUpTo(navController.graph.id) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            }
        )
    }
}
