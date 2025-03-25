package com.project.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.home.domain.model.Background
import com.project.home.domain.model.Experience
import com.project.theme.appTheme

@Composable
fun ExperienceSection(
    title: String,
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    background: List<Background>,
) {
    val isCompact = windowSize.widthSizeClass == WindowWidthSizeClass.Compact

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeadingText(
            title = title
        )
        background.forEach { item ->
            ExperienceContainer(background = item, isCompact = isCompact)

        }
    }
}

@Composable
private fun ExperienceContainer(
    isCompact: Boolean = false,
    modifier: Modifier = Modifier,
    background: Background
) {
    if (isCompact) {
        ExperienceCompact(
            modifier = modifier,
            background = background
        )
    } else {
        ExperienceExpanded(
            modifier = modifier,
            background = background
        )
    }
}

@Composable
private fun ExperienceCompact(
    modifier: Modifier = Modifier,
    background: Background,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {

    }
}

@Composable
private fun ExperienceExpanded(
    modifier: Modifier = Modifier,
    background: Background,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                background.headerTitle,
                style = appTheme.responsiveTypography.bodyMedium.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
        Spacer(Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()

        ) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally)
            ) {
                items(background.experience) {
                    ExperienceItemTile(experience = it)
                }
            }
        }

    }
}

@Composable
private fun ExperienceItemTile(
    modifier: Modifier = Modifier,
    experience: Experience
) {
    Column(
        modifier = modifier
            .width(250.dp)
            .defaultMinSize(
                minHeight = 220.dp
            )
            .background(
                MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 16.dp, vertical = 8.dp),

        ) {
        Text(
            experience.title,
            style = appTheme.responsiveTypography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
            )
        )
        Text(
            experience.date,
            style = appTheme.responsiveTypography.bodySmall.copy(
                fontWeight = FontWeight.Normal,
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.tertiary
            )
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        Text(
            experience.description,
            style = appTheme.responsiveTypography.bodySmall.copy(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.outline,
            )
        )
    }
}
