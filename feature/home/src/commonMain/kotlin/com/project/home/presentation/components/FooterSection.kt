package com.project.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.home.presentation.viewmodel.HeaderSectionType
import com.project.theme.appTheme
import com.project.utils.DateHelper

@Composable
fun FooterSection(
    modifier: Modifier = Modifier,
    windowSize: WindowSizeClass,
    section: List<HeaderSectionType>,
    onSectionClicked: (HeaderSectionType) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
            .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 24.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            VerticalDivider(thickness = 8.dp, color = Color.Magenta)
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ){
                FooterNavSection(
                    section = section,
                    onSectionClicked = onSectionClicked
                )

            }

            Row (
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                FooterDetailSection(
                    name = "Vincent Ola-ola",
                    techStack = "Kotlin Multiplatform"
                )
            }

        }

        Spacer(Modifier.height(12.dp))
        Text(
            text = "© ${DateHelper.getCurrentYear()} Vince-biins. All rights reserved.",
            style = appTheme.responsiveTypography.bodySmall.copy(
                color = MaterialTheme.colorScheme.outline
            )
        )
    }
}

@Composable
private fun FooterNavSection(
    modifier: Modifier = Modifier,
    section: List<HeaderSectionType>,
    onSectionClicked: (HeaderSectionType) -> Unit,
) {
    val sideBorderColor = MaterialTheme.colorScheme.onBackground
    Box(
        modifier = modifier
            .drawBehind {
                drawLine(
                    color = sideBorderColor,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            }
    ) {
        Column(
            modifier = Modifier.padding(start = 12.dp),
            verticalArrangement = Arrangement.Bottom,
        ) {
            section.forEach {
                TextHeaderClickable(
                    text = it.text,
                    onClick = {
                        onSectionClicked(it)
                    }
                )
            }
        }
    }
}

@Composable
private fun FooterDetailSection(
    modifier: Modifier = Modifier,
    name: String,
    techStack: String,
) {
    val sideBorderColor = MaterialTheme.colorScheme.onBackground
    Box(
        modifier = modifier
            .size(200.dp)
            .drawBehind {
                drawLine(
                    color = sideBorderColor,
                    start = Offset(0f, 0f),
                    end = Offset(0f, size.height),
                    strokeWidth = 2.dp.toPx()
                )
            }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(start = 16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically)
        ) {

            Column {
                Text(
                    text = "Developed by:",
                    style = appTheme.responsiveTypography.bodySmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = name,
                    style = appTheme.responsiveTypography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.outline
                    )
                )
            }

            Column {
                Text(
                    text = "Technology used:",
                    style = appTheme.responsiveTypography.bodySmall.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
                Text(
                    text = techStack,
                    style = appTheme.responsiveTypography.bodySmall.copy(
                        color = MaterialTheme.colorScheme.outline
                    )
                )
            }
        }
    }

}
