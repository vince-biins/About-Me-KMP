package com.project.home.presentation.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.project.composables.buttons.CoilImageWithError2
import com.project.home.domain.model.Expertise
import com.project.home.domain.model.TechSkill
import com.project.theme.appTheme
import kotlinx.coroutines.launch

@Composable
fun SkillSection(
    title: String,
    scrollState: LazyListState,
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    expertise: List<Expertise>
) {
    val isCompact = windowSize.widthSizeClass == WindowWidthSizeClass.Compact

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 16.dp, vertical = 24.dp)
    ) {
        SectionHeadingText(title = title)
        expertise.forEach { skill ->
            ExpertiseCarousel(
                isCompact = isCompact,
                techSkill = skill.skill,
                carouselTitle = skill.title,
                rootScrollState = scrollState
            )
        }
    }
}

@Composable
private fun ExpertiseCarousel(
    rootScrollState: LazyListState,
    isCompact: Boolean,
    maskingColor: Color = MaterialTheme.colorScheme.surface,
    techSkill: List<TechSkill>,
    carouselTitle: String,
) {
    val horizontalScrollState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    val containerAlpha = remember { Animatable(0f) }
    val translateY = remember { Animatable(30f) }
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(isVisible) {
        if (isVisible) {
            containerAlpha.animateTo(1f, animationSpec = tween(durationMillis = 800))
            translateY.animateTo(0f, animationSpec = tween(durationMillis = 800))
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .alpha(containerAlpha.value)
            .graphicsLayer { translationY = translateY.value }
            .onGloballyPositioned { layoutCoordinates ->
                val containerBounds = layoutCoordinates.boundsInRoot()

                val layoutInfo = rootScrollState.layoutInfo

                val viewportTop = layoutInfo.viewportStartOffset.toFloat()
                val viewportBottom = layoutInfo.viewportEndOffset.toFloat()

                val isNowVisible =
                    containerBounds.bottom > viewportTop && containerBounds.top < viewportBottom

                if (isNowVisible && !isVisible) {
                    isVisible = true
                }
            },
    ) {
        Text(
            carouselTitle,
            style = appTheme.responsiveTypography.bodyMedium.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        )
        val widthSize = if (isCompact) 1f else 0.8f
        Box(
            modifier = Modifier
                .fillMaxWidth(widthSize)
                .height(100.dp)
        ) {
            LazyRow(
                state = horizontalScrollState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp).draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { delta ->
                            coroutineScope.launch {
                                horizontalScrollState.scrollBy(-delta)
                            }
                        },
                    ),
                horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(techSkill) {
                    TechSkillTile(
                        techSkill = it,
                        modifier = Modifier.size(
                            width = if(isCompact) 120.dp else 160.dp,
                            height = 90.dp
                        )
                    )
                }
            }

            Box(
                modifier = Modifier
                    .matchParentSize()
                    .drawWithContent {
                        drawContent()

                        val width = size.width
                        // Left fade
                        drawRect(
                            brush = Brush.horizontalGradient(
                                colors = listOf(maskingColor, Color.Transparent),
                                startX = 0f,
                                endX = width * 0.10f
                            ),
                            size = size
                        )

                        // Right fade
                        drawRect(
                            brush = Brush.horizontalGradient(
                                colors = listOf(Color.Transparent, maskingColor),
                                startX = width * 0.80f,
                                endX = width
                            ),
                            size = size
                        )
                    }
                    .graphicsLayer {
                        alpha = 1f
                        compositingStrategy = CompositingStrategy.ModulateAlpha
                    }
            )
        }
    }
}

@Composable
private fun TechSkillTile(
    modifier: Modifier = Modifier,
    techSkill: TechSkill,
) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(4.dp),
            )
    ) {
        Column(
            modifier = modifier.background(
                MaterialTheme.colorScheme.surfaceContainer,
                shape = RoundedCornerShape(4.dp)
            ).padding(12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoilImageWithError2(
                imageUrl = techSkill.imageUrl,
                modifier = Modifier.size(32.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(8.dp))
            BoxWithConstraints {
                val fontSize = when {
                    maxWidth < 140.dp -> 12.sp
                    maxWidth < 160.dp -> 13.sp
                    else -> 14.sp
                }

                Text(
                    text = techSkill.title,
                    fontSize = fontSize,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}