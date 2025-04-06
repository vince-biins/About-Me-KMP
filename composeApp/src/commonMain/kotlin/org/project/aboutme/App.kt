package org.project.aboutme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import coil3.compose.setSingletonImageLoaderFactory
import com.project.home.di.homeModule
import com.project.home.presentation.navigation.HomeRoute
import com.project.home.presentation.navigation.homeRoute
import com.project.theme.AboutMeTheme
import com.project.utils.getAsyncImageLoader
import org.koin.compose.KoinApplication

@Composable
fun App() {
    KoinApplication(
        application = {
            modules(
                homeModule,
            )
        }
    ) {
         setSingletonImageLoaderFactory{ context ->
            getAsyncImageLoader(context)
        }

        AboutMeTheme (darkTheme = true) {
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
