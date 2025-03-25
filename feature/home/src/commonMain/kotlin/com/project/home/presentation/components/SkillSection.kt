package com.project.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.home.domain.model.Expertise
import com.project.home.domain.model.Skill
import com.project.theme.appTheme

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillSection(
    title: String,

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
        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            maxItemsInEachRow = 2,

            ) {
            expertise.forEach { skill ->
                SkillTile(
                    isCompact = isCompact,
                    expertise = skill
                )

            }
        }
    }
}

@Composable
private fun SkillTile(
    isCompact: Boolean,
    modifier: Modifier = Modifier,
    expertise: Expertise
) {
    Box(
        modifier = modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(4.dp),

            )
    ) {
        Column(
            modifier = modifier
                .width(if (isCompact) 160.dp else 250.dp)
                .defaultMinSize(
                    minHeight = 220.dp
                )
                .background(
                    MaterialTheme.colorScheme.surfaceContainer,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {

            Box {
                Icon(
                    Icons.Filled.MailOutline,
                    "",
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                        .size(60.dp)
                )
            }
            Text(
                expertise.title,
                style = appTheme.responsiveTypography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )
            )
            Spacer(Modifier.height(12.dp))
            expertise.skill.forEach { skill ->
                Text(
                    skill.title,
                    style = appTheme.responsiveTypography.bodySmall.copy(
                        fontWeight = FontWeight.Normal,
                    )
                )
                if (skill.subTitle.isNotEmpty()) {
                    skill.subTitle.forEach { subSkill ->
                        Text(
                            text = subSkill,
                            style = appTheme.responsiveTypography.bodySmall.copy(
                                fontWeight = FontWeight.Normal,
                            ),
                            modifier = Modifier.padding(start = 24.dp),
                        )
                    }
                }
            }
        }
    }
}