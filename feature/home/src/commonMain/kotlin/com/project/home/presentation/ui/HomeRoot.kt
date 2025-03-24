package com.project.home.presentation.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.project.home.domain.model.Profile
import com.project.home.presentation.components.HeaderSection
import com.project.home.presentation.components.IntroSection
import com.project.home.presentation.viewmodel.HomeViewModel

@Composable
fun HomeRoot(viewModel: HomeViewModel, navController: NavController) {

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
) {
    Scaffold() { innerPadding ->
        HomeContent(
            profile = profile,
            modifier = Modifier.padding(innerPadding),
            onHomeClicked = onHomeClicked,
            onAboutClicked = onAboutClicked,
            onContactClicked = onContactClicked,
            onExperienceClicked = onExperienceClicked,
            onSkillsClicked = onSkillsClicked
        )
    }
}

@Composable
fun HomeContent(
    profile: Profile,
    modifier: Modifier = Modifier,
    onHomeClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onExperienceClicked: () -> Unit,
    onSkillsClicked: () -> Unit,
    onContactClicked: () -> Unit,
) {
    Column {
        HeaderSection(
            onHomeClicked = onHomeClicked,
            onAboutClicked = onAboutClicked,
            onContactClicked = onContactClicked,
            onExperienceClicked = onExperienceClicked,
            onSkillsClicked = onSkillsClicked
        )

        IntroSection(
            superHeading = "Hello",
            mainHeading = profile.basicProfile.headerTitle,
            subHeading = profile.basicProfile.headerSubtitle,
            onClickIntroAbout = {},
        )
    }

}