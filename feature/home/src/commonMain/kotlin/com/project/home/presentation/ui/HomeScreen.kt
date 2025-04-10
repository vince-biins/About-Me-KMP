package com.project.home.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.project.home.domain.model.Profile
import com.project.home.presentation.components.HeaderSection
import com.project.home.presentation.viewmodel.HeaderSectionType

@Composable
fun DesktopWebHomeScreen(
    scrollState: LazyListState,
    windowSize: WindowSizeClass,
    profile: Profile,
    sections: List<HeaderSectionType>,
    onSectionClicked: (HeaderSectionType) -> Unit,
    onClickIntroAbout: () -> Unit,
    onClickDownloadCV: (String) -> Unit,
    onRefresh: () -> Unit,
    onDrawerClicked: () -> Unit,
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
                    sections = sections,
                    onClickIntroAbout = onClickIntroAbout,
                    onClickDownloadCV = onClickDownloadCV,
                    onSectionClicked = onSectionClicked,
                )
            }
            HeaderSection(
                windowSize = windowSize,
                section = sections,
                onSectionClicked = onSectionClicked,
                onDrawerClicked = onDrawerClicked,
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
    sections: List<HeaderSectionType>,
    onSectionClicked: (HeaderSectionType) -> Unit,
    onClickIntroAbout: () -> Unit,
    onClickDownloadCV: (String) -> Unit,
    onRefresh: () -> Unit,
    onDrawerClicked: () -> Unit,
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

                ) {
                    HomeContent(
                        scrollState = scrollState,
                        windowSize = windowSize,
                        profile = profile,
                        sections = sections,
                        onClickIntroAbout = onClickIntroAbout,
                        onClickDownloadCV = onClickDownloadCV,
                        onSectionClicked = onSectionClicked
                    )
                }
                HeaderSection(
                    windowSize = windowSize,
                    section = sections,
                    onSectionClicked = onSectionClicked,
                    onDrawerClicked = onDrawerClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .zIndex(1f)
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.8f))
                )
            }
        }
    }
}
