package org.project.aboutme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.project.home.di.homeModule
import com.project.home.presentation.navigation.HomeRoute
import com.project.home.presentation.navigation.homeRoute
import com.project.theme.AboutMeTheme
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(
        application = {
            modules(
                homeModule
            )
        }
    ) {
        AboutMeTheme {
            val navController = rememberNavController()
            createNavGraph(navController = navController)
        }
    }
}

@Composable
fun createNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HomeRoute) {
        homeRoute(navController)
    }
}