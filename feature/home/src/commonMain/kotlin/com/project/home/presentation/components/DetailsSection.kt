package com.project.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.composables.buttons.RoundedCornerButton
import com.project.home.domain.model.DetailedProfile
import com.project.theme.appTheme

@Composable
fun DetailsSection(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    profile: DetailedProfile,
    onClickDownloadCV: () -> Unit,
) {
    val sectionHeadingText = "About Myself"
    when (windowSize.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                SectionHeadingText(
                    title = sectionHeadingText
                )

                HorizontalDivider()
                DetailContainer(
                    isCompact = true,
                    profile = profile,
                    onClickDownloadCV = onClickDownloadCV,
                )
            }
        }

        else -> {

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 64.dp, vertical = 32.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    SectionHeadingText(
                        title = sectionHeadingText
                    )
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(12.dp))
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    DetailContainer(
                        profile = profile,
                        onClickDownloadCV = onClickDownloadCV
                    )
                }
            }
        }
    }
}

@Composable
fun DetailContainer(
    isCompact: Boolean = false,
    modifier: Modifier = Modifier,
    profile: DetailedProfile,
    onClickDownloadCV: () -> Unit,
) {
    Column(
        modifier = modifier.padding(12.dp),

    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(if(isCompact) 8.dp else 12.dp)
        ) {
            Text(
                text = "Who am I?",
                style = appTheme.responsiveTypography.bodySmall.copy(
                    color = MaterialTheme.colorScheme.tertiary
                )
            )

            Text(
                text = profile.title,
                style = appTheme.responsiveTypography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                )
            )

            Text(
                text = profile.description,
                style = appTheme.responsiveTypography.bodySmall.copy(
                    textAlign = TextAlign.Justify,
                    lineHeight = 24.sp
                )
            )
        }

        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DetailField(
                modifier = Modifier.weight(1f),
                title = "Name:",
                description = profile.name
            )
            DetailField(
                modifier = Modifier.weight(1f),
                title = "From:",
                description = profile.location
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            DetailField(
                modifier = Modifier.weight(1f),
                title = "Age:",
                description = profile.age.toString().plus(" yrs old")
            )
            DetailField(
                modifier = Modifier.weight(1f),
                title = "Email:",
                description = profile.email
            )
        }
        Spacer(Modifier.height(24.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            RoundedCornerButton(
                onClick = onClickDownloadCV,
                text = "Download CV",
            )
        }

    }
}

@Composable
fun DetailField(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = appTheme.responsiveTypography.bodySmall.copy(
                fontWeight = FontWeight.Bold
            ),
        )
        Text(
            text = description,
            style = appTheme.responsiveTypography.bodySmall,
        )
    }
}