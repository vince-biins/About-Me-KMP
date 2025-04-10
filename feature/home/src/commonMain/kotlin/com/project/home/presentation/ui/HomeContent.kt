package com.project.home.presentation.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.home.domain.model.Profile
import com.project.home.presentation.components.ContactSection
import com.project.home.presentation.components.DetailsSection
import com.project.home.presentation.components.ExperienceSection
import com.project.home.presentation.components.FooterSection
import com.project.home.presentation.components.IntroSection
import com.project.home.presentation.components.ProjectSection
import com.project.home.presentation.components.SkillSection
import com.project.home.presentation.viewmodel.HeaderSectionType


@Composable
fun HomeContent(
    modifier: Modifier = Modifier,
    scrollState: LazyListState,
    profile: Profile,
    sections: List<HeaderSectionType>,
    windowSize: WindowSizeClass,
    onClickIntroAbout: () -> Unit,
    onClickDownloadCV: (String) -> Unit,
    onSectionClicked: (HeaderSectionType) -> Unit,
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
            ProjectSection(
                title = "Projects",
                windowSize = windowSize,
                project = profile.projects,
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
                section = sections,
                onSectionClicked = onSectionClicked,
            )
        }

    }
}