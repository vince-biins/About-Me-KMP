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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.composables.buttons.CoilImageWithError2
import com.project.home.domain.model.Project
import com.project.theme.appTheme


private object ProjectSectionConstants{
     val  tileMinHeightSize = 300.dp
     val ROUNDED_CORNER_RADIUS = 8.dp
}

@Composable
fun ProjectSection(
    title: String,
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    project: List<Project>,
    scrollState: LazyListState,
) {
    val containerAlpha = remember { Animatable(0f) }
    val translateY = remember { Animatable(30f) }
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(isVisible) {
        if (isVisible) {
            containerAlpha.animateTo(1f, animationSpec = tween(durationMillis = 800))
            translateY.animateTo(0f, animationSpec = tween(durationMillis = 800))
        }
    }

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
        project.forEach {
            ProjectTile(
                project = it,
                modifier = Modifier .alpha(containerAlpha.value)
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
                isCompact = isCompact
            )
        }

    }
}


@Composable
fun ProjectTile(
    modifier: Modifier = Modifier,
    project: Project,
    isCompact: Boolean,
) {
    val mod = if(isCompact) modifier else modifier.fillMaxWidth(0.5f)
    Box(
        modifier = mod.shadow(
            elevation = 4.dp,
            shape = RoundedCornerShape(ProjectSectionConstants.ROUNDED_CORNER_RADIUS)
        )
    ) {
        Column(
            modifier = Modifier
                .defaultMinSize(minHeight = ProjectSectionConstants.tileMinHeightSize)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(ProjectSectionConstants.ROUNDED_CORNER_RADIUS)
                ).clip(
                    shape = RoundedCornerShape(ProjectSectionConstants.ROUNDED_CORNER_RADIUS)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(ProjectSectionConstants.tileMinHeightSize / 2)
                    .background(MaterialTheme.colorScheme.outline)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                CoilImageWithError2(
                    imageUrl = project.imageUrl,
                    contentScale = ContentScale.FillHeight
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    project.title,
                    style = appTheme.responsiveTypography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                Text(
                    project.description,
                    style = appTheme.responsiveTypography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.outline
                    ),
                )
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    project.stack.forEach {
                        Box(
                            modifier = Modifier
                                .background(
                                    MaterialTheme.colorScheme.primary,
                                    shape = RoundedCornerShape(ProjectSectionConstants.ROUNDED_CORNER_RADIUS)
                                )
                                .padding(4.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = it,
                                style = appTheme.responsiveTypography.labelMedium.copy(
                                    color = MaterialTheme.colorScheme.onPrimary
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}