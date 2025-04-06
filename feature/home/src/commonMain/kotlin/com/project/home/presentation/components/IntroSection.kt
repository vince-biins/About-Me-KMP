package com.project.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.composables.buttons.CoilImageWithError2
import com.project.composables.buttons.RoundedCornerButton
import com.project.home.domain.model.BasicProfile
import com.project.theme.appTheme

@Composable
fun IntroSection(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    profile: BasicProfile,
    onClickIntroAbout: () -> Unit,
) {
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                ProfileImage(
                    imageUrl = profile.headerImageUrl,
                )

                IntroDescription(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                    isContentCentered = true,
                    superHeading = profile.headerSuperTItle,
                    mainHeading = profile.headerTitle,
                    subHeading = profile.headerSubtitle,
                    onClickIntroAbout = onClickIntroAbout,
                )

            }
        }

        else -> {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IntroDescription(

                    isContentCentered = false,
                    superHeading = profile.headerSuperTItle,
                    mainHeading = profile.headerTitle,
                    subHeading = profile.headerSubtitle,
                    onClickIntroAbout = onClickIntroAbout,
                )

                Spacer(Modifier.width(32.dp))
                Column {
                    ProfileImage(
                        imageUrl = profile.headerImageUrl,
                        isCircular = false,

                        )
                }
            }
        }
    }
}

@Composable
private fun ProfileImage(
    modifier: Modifier = Modifier,
    imageUrl: String,
    isCircular: Boolean = true,
) {
    val imageSize = if (isCircular) 240.dp else 420.dp
    Box(
        contentAlignment = Alignment.Center,
        modifier = if (isCircular) modifier
            .size(imageSize)
            .shadow(8.dp, shape = CircleShape)
            .border(
                width = 4.dp,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Magenta, Color.Cyan)
                ),
                shape = CircleShape
            )
            .clip(CircleShape)
            .background(Color.White) else {
            modifier.wrapContentSize()
        }
    ) {
        CoilImageWithError2(
            imageUrl = imageUrl,
            modifier = Modifier.size(imageSize),
        )
    }
}

@Composable
private fun IntroDescription(
    modifier: Modifier = Modifier,
    isContentCentered: Boolean = false,
    superHeading: String,
    mainHeading: String,
    subHeading: String,
    onClickIntroAbout: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = if (isContentCentered) Alignment.CenterHorizontally else Alignment.Start,
    ) {
        Text(
            superHeading,
            style = appTheme.responsiveTypography.headlineSmall.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            ),
        )

        Text(
            mainHeading,
            style = appTheme.responsiveTypography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                lineHeight = 32.sp
            ),
        )

        Text(
            subHeading,
            style = appTheme.responsiveTypography.headlineSmall.copy(
                fontWeight = FontWeight.Normal,
                lineHeight = 24.sp
            ),
        )

        RoundedCornerButton(
            onClick = onClickIntroAbout,
            text = "About Me",
        )
    }
}