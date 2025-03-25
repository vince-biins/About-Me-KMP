package com.project.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.project.home.domain.model.Profile
import com.project.home.presentation.components.DetailsSection
import com.project.home.presentation.components.HeaderSection
import com.project.home.presentation.components.IntroSection
import com.project.home.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeRoot(viewModel: HomeViewModel, navController: NavController) {
    val windowSize = calculateWindowSizeClass()
    val state by viewModel.state.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
            HomeScreen(
                windowSize = windowSize,
                profile = state.profile!!,
                onHomeClicked = {},
                onContactClicked = {},
                onAboutClicked = {},
                onSkillsClicked = {},
                onExperienceClicked = {},
            )
        }
    }
}

@Composable
fun HomeScreen(

    profile: Profile,
    onHomeClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onExperienceClicked: () -> Unit,
    onSkillsClicked: () -> Unit,
    onContactClicked: () -> Unit,
    windowSize: WindowSizeClass,
) {
    Scaffold() { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(top = 64.dp)
            ) {
                HomeContent(
                    windowSize = windowSize,
                    profile = profile,
                )
            }
            HeaderSection(
                windowSize = windowSize,
                onHomeClicked = onHomeClicked,
                onAboutClicked = onAboutClicked,
                onContactClicked = onContactClicked,
                onExperienceClicked = onExperienceClicked,
                onSkillsClicked = onSkillsClicked,
                modifier = Modifier
                    .fillMaxWidth()
                    .zIndex(1f)
                    .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
            )
        }
    }
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    profile: Profile,
) {

    Column {
        if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
            Spacer(Modifier.height(64.dp))
        }
        IntroSection(
            windowSize = windowSize,
            profile = profile.basicProfile,
            onClickIntroAbout = {},
        )
        Spacer(Modifier.height(32.dp))
        DetailsSection(
            windowSize = windowSize,
            profile = profile.detailedProfile,
            onClickDownloadCV = {},
        )
    }
}