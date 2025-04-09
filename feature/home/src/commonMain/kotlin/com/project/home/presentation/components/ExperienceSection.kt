package com.project.home.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.composables.buttons.CoilImageWithError2
import com.project.home.domain.model.Background
import com.project.home.domain.model.Experience
import com.project.theme.appTheme
import kotlin.math.exp

@Composable
fun ExperienceSection(
    scrollState: LazyListState,
    title: String,
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    background: List<Background>,
) {
    val isCompact = windowSize.widthSizeClass == WindowWidthSizeClass.Compact

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        SectionHeadingText(
            title = title
        )
        background.forEach { item ->
            ExperienceContainer(background = item, isCompact = isCompact, scrollState = scrollState)

        }
    }
}


@Composable
private fun ExperienceContainer(
    isCompact: Boolean = false,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
    background: Background,
) {

    val alpha = remember { Animatable(0f) }
    val translateY = remember { Animatable(30f) }
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(isVisible) {
        if (isVisible) {
            alpha.animateTo(1f, animationSpec = tween(durationMillis = 800))
            translateY.animateTo(0f, animationSpec = tween(durationMillis = 800))
        }
    }

    Column(
        modifier = modifier.fillMaxWidth()
            .alpha(alpha.value)
            .graphicsLayer { translationY = translateY.value }
            .onGloballyPositioned { layoutCoordinates ->
                val containerBounds = layoutCoordinates.boundsInRoot()

                val layoutInfo = scrollState.layoutInfo

                val viewportTop = layoutInfo.viewportStartOffset.toFloat()
                val viewportBottom = layoutInfo.viewportEndOffset.toFloat()

                val isNowVisible =
                    containerBounds.bottom > viewportTop && containerBounds.top < viewportBottom

                if (isNowVisible && !isVisible) {
                    isVisible = true
                }
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Spacer(Modifier.height(12.dp))
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
        if (isCompact) {
            background.experience.reversed().forEach {

                ExperienceItemTile2(
                    experience = it
                )
            }
        } else {
            Spacer(Modifier.height(12.dp))
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
fun ExperienceItemTile2(
    modifier: Modifier = Modifier,
    experience: Experience
) {
    Box(
        modifier = Modifier.shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(4.dp),
        )
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(4.dp)
                ).padding(12.dp),
        ) {
            Box {
                CoilImageWithError2(
                    imageUrl = experience.imageUrl,
                    modifier = Modifier.size(64.dp).clip(
                        RoundedCornerShape(16.dp)
                    )
                )
            }
            Spacer(Modifier.width(12.dp))
            Column {
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
                    modifier = Modifier.height(8.dp)
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
    }
}

@Composable
private fun ExperienceItemTile(
    modifier: Modifier = Modifier,
    experience: Experience
) {
    Box(
        modifier = modifier
            .width(250.dp)
            .defaultMinSize(
                minHeight = 220.dp
            ).shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(4.dp),
                clip = false
            )
            .background(
                MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(4.dp)
            )

            .padding(horizontal = 16.dp, vertical = 8.dp)

    ) {


        Column(modifier = Modifier.fillMaxSize().padding(top = 24.dp)) {
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

        Box(

            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxWidth().offset(y = (-32).dp)
        ) {
            CoilImageWithError2(
                imageUrl = experience.imageUrl,
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(16.dp))
            )
        }
    }
}
