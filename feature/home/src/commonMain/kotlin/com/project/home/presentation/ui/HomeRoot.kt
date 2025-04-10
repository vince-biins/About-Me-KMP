package com.project.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
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
import androidx.navigation.NavController
import com.project.home.domain.model.Profile
import com.project.home.presentation.components.CustomNavigationDrawer
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
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    LaunchedEffect(windowSize) {
        if(windowSize.widthSizeClass != WindowWidthSizeClass.Compact) {
            if (drawerState.isOpen) {
                drawerState.close()
            }
        }
    }
    LaunchedEffect(Unit) {

        viewModel.event.collectLatest { event ->
            if (event is HomeEvent.OnHeaderClicked) {
                scrollState.animateScrollToItem(event.type.itemPosition)
                if (drawerState.isOpen) {
                    drawerState.close()
                }
            }

            if(event is HomeEvent.OnNavigationDrawerClicked) {
                if (drawerState.isClosed) {
                    drawerState.open()
                } else {
                    drawerState.close()
                }
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
                drawerState = drawerState,
                windowSize = windowSize,
                profile = state.profile!!,
                onClickIntroAbout = {
                    viewModel.onEvent(HomeEvent.OnHeaderClicked(HeaderSectionType.ABOUT_ME))
                },
                onClickDownloadCV = {
                    viewModel.onEvent(HomeEvent.OnDownloadCvClicked(it))
                },
                onRefresh = {
                    viewModel.onEvent(HomeEvent.OnRefresh)
                },
                sections = viewModel.sectionHeaders,
                onSectionClicked = { section ->
                    viewModel.onEvent(HomeEvent.OnHeaderClicked(section))
                },
                onDrawerClicked = {
                    viewModel.onEvent(HomeEvent.OnNavigationDrawerClicked)
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
    drawerState: DrawerState,
    windowSize: WindowSizeClass,
    profile: Profile,
    sections: List<HeaderSectionType>,
    onSectionClicked: (HeaderSectionType) -> Unit,
    onClickIntroAbout: () -> Unit,
    onClickDownloadCV: (String) -> Unit,
    onRefresh: () -> Unit,
    onDrawerClicked: () -> Unit,
) {
    val platform = platformName()
    CustomNavigationDrawer(
        drawerState = drawerState,
        gestureEnable = windowSize.widthSizeClass == WindowWidthSizeClass.Compact,
        sectionType = sections,
        onSectionClicked = onSectionClicked,
    ) {
        when (platform) {
            Platform.ANDROID,
            Platform.IOS -> AndroidHomeScreen(
                scrollState = scrollState,
                windowSize = windowSize,
                profile = profile,
                onSectionClicked = onSectionClicked,
                sections = sections,
                onClickIntroAbout = onClickIntroAbout,
                onClickDownloadCV = onClickDownloadCV,
                isRefreshing = isRefreshing,
                onRefresh = onRefresh,
                onDrawerClicked = onDrawerClicked,
            )

            Platform.DESKTOP,
            Platform.WEB -> DesktopWebHomeScreen(
                scrollState = scrollState,
                windowSize = windowSize,
                profile = profile,
                onSectionClicked = onSectionClicked,
                sections = sections,
                onClickIntroAbout = onClickIntroAbout,
                onClickDownloadCV = onClickDownloadCV,
                onRefresh = onRefresh,
                onDrawerClicked = onDrawerClicked,
            )
        }

    }
}

