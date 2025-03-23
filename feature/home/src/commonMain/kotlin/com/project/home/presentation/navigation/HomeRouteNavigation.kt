package com.project.home.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.project.home.presentation.ui.HomeRoot
import com.project.home.presentation.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel

fun NavGraphBuilder.homeRoute(navController: NavHostController) {
    composable<HomeRoute> {
        val viewModel: HomeViewModel = koinViewModel()
        HomeRoot(viewModel = viewModel, navController = navController)
    }
}
