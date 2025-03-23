package com.project.home.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.home.domain.model.Profile
import com.project.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeRoot(viewModel: HomeViewModel, navController: NavController) {

    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        if (state.isLoading) {
            CircularProgressIndicator()
            Text(text = "Loading...", modifier = Modifier.padding(top = 8.dp))
        }


        if (state.error != null) {
            Text(
                text = state.error!!,
                color = MaterialTheme.colorScheme.error
            )
        }

        if (state.profile != null) {
            HomeScreen(profile = state.profile!!)
        }
    }
}

@Composable
fun HomeScreen(profile: Profile) {
    Column {
        Text(
            text = profile.basicProfile.headerTitle,
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = profile.basicProfile.headerSubtitle,
        )
        Text(
            text = profile.toString()
        )
    }
}

@Composable
fun HomeContent() {

}