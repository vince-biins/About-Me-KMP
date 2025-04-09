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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import com.project.home.domain.model.Profile
import com.project.home.presentation.components.ContactSection
import com.project.home.presentation.components.DetailsSection
import com.project.home.presentation.components.ExperienceSection
import com.project.home.presentation.components.FooterSection
import com.project.home.presentation.components.HeaderSection
import com.project.home.presentation.components.IntroSection
import com.project.home.presentation.components.SkillSection
import com.project.home.presentation.viewmodel.HeaderSectionType
import com.project.home.presentation.viewmodel.HomeEvent
import com.project.home.presentation.viewmodel.HomeViewModel
import com.project.utils.platform.Platform
import com.project.utils.platform.platformName
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun HomeRoot(viewModel: HomeViewModel, navController: NavController) {
    val windowSize = calculateWindowSizeClass()
    val state by viewModel.state.collectAsState()

    val scrollState = rememberLazyListState()

    LaunchedEffect(Unit) {
        viewModel.event.collectLatest { event ->
            if(event is HomeEvent.OnHeaderClicked) {
                scrollState.animateScrollToItem(event.type.itemPosition)
            }
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center

    ) {


        if (state.error != null) {
            Text(
                text = state.error!!,
                color = MaterialTheme.colorScheme.error
            )
        }

        if (state.profile != null) {
            HomeScreen(
                isRefreshing = state.isRefreshing,
                scrollState = scrollState,
                windowSize = windowSize,
                profile = state.profile!!,
                onHomeClicked = {
                    viewModel.onEvent(HomeEvent.OnHeaderClicked(HeaderSectionType.HOME))
                },
                onContactClicked = {
                    viewModel.onEvent(HomeEvent.OnHeaderClicked(HeaderSectionType.CONTACT_US))
                },
                onAboutClicked = {
                    viewModel.onEvent(HomeEvent.OnHeaderClicked(HeaderSectionType.ABOUT_ME))
                },
                onSkillsClicked = {
                    viewModel.onEvent(HomeEvent.OnHeaderClicked(HeaderSectionType.EXPERTISE))
                },
                onExperienceClicked = {
                    viewModel.onEvent(HomeEvent.OnHeaderClicked(HeaderSectionType.EXPERIENCE))
                },
                onClickIntroAbout = {
                    viewModel.onEvent(HomeEvent.OnHeaderClicked(HeaderSectionType.ABOUT_ME))
                },
                onClickDownloadCV = {
                    viewModel.onEvent(HomeEvent.OnDownloadCvClicked(it))
                },
                onRefresh = {
                    viewModel.onEvent(HomeEvent.OnRefresh)
                }
            )
        }

        if (state.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize().background(
                    MaterialTheme.colorScheme.background.copy(
                        alpha = 0.7f
                    )
                ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
         }


    }
}

@Composable
fun HomeScreen(
    isRefreshing: Boolean,
    scrollState: LazyListState,
    windowSize: WindowSizeClass,
    profile: Profile,
    onHomeClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onExperienceClicked: () -> Unit,
    onSkillsClicked: () -> Unit,
    onContactClicked: () -> Unit,
    onClickIntroAbout: () -> Unit,
    onClickDownloadCV: (String) -> Unit,
    onRefresh: () -> Unit,
) {
    when (platformName()) {
       Platform.ANDROID,
        Platform.IOS -> AndroidHomeScreen(
           scrollState = scrollState,
           windowSize = windowSize,
           profile = profile,
           onClickIntroAbout = onClickIntroAbout,
           onClickDownloadCV = onClickDownloadCV,
           onHomeClicked = onHomeClicked,
           onAboutClicked = onAboutClicked,
           onContactClicked = onContactClicked,
           onExperienceClicked = onExperienceClicked,
           onSkillsClicked = onSkillsClicked,
           isRefreshing = isRefreshing,
           onRefresh = onRefresh,
       )
        Platform.DESKTOP,
        Platform.WEB -> DesktopWebHomeScreen(
            scrollState = scrollState,
            windowSize = windowSize,
            profile = profile,
            onClickIntroAbout = onClickIntroAbout,
            onClickDownloadCV = onClickDownloadCV,
            onHomeClicked = onHomeClicked,
            onAboutClicked = onAboutClicked,
            onContactClicked = onContactClicked,
            onExperienceClicked = onExperienceClicked,
            onSkillsClicked = onSkillsClicked,
            onRefresh = onRefresh,
        )

    }
}

@Composable
fun DesktopWebHomeScreen(
    scrollState: LazyListState,
    windowSize: WindowSizeClass,
    profile: Profile,
    onHomeClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onExperienceClicked: () -> Unit,
    onSkillsClicked: () -> Unit,
    onContactClicked: () -> Unit,
    onClickIntroAbout: () -> Unit,
    onClickDownloadCV: (String) -> Unit,
    onRefresh: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onRefresh,
                content = {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh",
                        tint = Color.White
                    )
                }
            )
        }
    ) { innerPadding ->

            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp)
                ) {
                    HomeContent(
                        scrollState = scrollState,
                        windowSize = windowSize,
                        profile = profile,
                        onClickIntroAbout = onClickIntroAbout,
                        onClickDownloadCV = onClickDownloadCV,
                        onHomeClicked = onHomeClicked,
                        onAboutClicked = onAboutClicked,
                        onContactClicked = onContactClicked,
                        onExperienceClicked = onExperienceClicked,
                        onSkillsClicked = onSkillsClicked,
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AndroidHomeScreen(
    isRefreshing: Boolean,
    scrollState: LazyListState,
    windowSize: WindowSizeClass,
    profile: Profile,
    onHomeClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onExperienceClicked: () -> Unit,
    onSkillsClicked: () -> Unit,
    onContactClicked: () -> Unit,
    onClickIntroAbout: () -> Unit,
    onClickDownloadCV: (String) -> Unit,
    onRefresh: () -> Unit,
) {
    Scaffold { innerPadding ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = onRefresh,
        ) {

            Box(
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 64.dp)
                ) {
                    HomeContent(
                        scrollState = scrollState,
                        windowSize = windowSize,
                        profile = profile,
                        onClickIntroAbout = onClickIntroAbout,
                        onClickDownloadCV = onClickDownloadCV,
                        onHomeClicked = onHomeClicked,
                        onAboutClicked = onAboutClicked,
                        onContactClicked = onContactClicked,
                        onExperienceClicked = onExperienceClicked,
                        onSkillsClicked = onSkillsClicked,
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
}

@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
    profile: Profile,
    windowSize: WindowSizeClass,
    onClickIntroAbout: () -> Unit,
    onClickDownloadCV: (String) -> Unit,
    onHomeClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onExperienceClicked: () -> Unit,
    onSkillsClicked:() -> Unit,
    onContactClicked:() -> Unit,
) {

    LazyColumn(
        state = scrollState
    ) {
        item {
            if (windowSize.widthSizeClass == WindowWidthSizeClass.Compact) {
                Spacer(Modifier.height(64.dp))
            }
            profile.basicProfile?.let {
                IntroSection(
                    windowSize = windowSize,
                    profile = it,
                    onClickIntroAbout = onClickIntroAbout,
                )
            }
        }
        item {
            Spacer(Modifier.height(32.dp))
            profile.detailedProfile?.let {
                DetailsSection(
                    windowSize = windowSize,
                    profile = it,
                    onClickDownloadCV = onClickDownloadCV,
                )
            }
        }

        item {
            Spacer(Modifier.height(32.dp))
            ExperienceSection(
                scrollState = scrollState,
                title = "Professional Journey",
                windowSize = windowSize,
                background = profile.background,
            )
        }

        item {
            Spacer(Modifier.height(32.dp))
            SkillSection(
                title = "Expertise",
                windowSize = windowSize,
                expertise = profile.skills,
                scrollState = scrollState
            )
        }

        item {
            Spacer(Modifier.height(32.dp))
            ContactSection(
                title = "Connect 2 ME",
                windowSize = windowSize,
                contacts = profile.contact
            )
        }

        item {
            Spacer(Modifier.height(32.dp))
            FooterSection(
                windowSize = windowSize,
                onHomeClicked = onHomeClicked,
                onAboutClicked = onAboutClicked,
                onExperienceClicked = onExperienceClicked,
                onSkillsClicked = onSkillsClicked,
                onContactClicked = onContactClicked,
            )
        }

    }
}