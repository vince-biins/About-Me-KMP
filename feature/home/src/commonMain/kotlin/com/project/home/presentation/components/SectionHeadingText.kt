package com.project.home.presentation.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.project.theme.appTheme

@Composable
fun SectionHeadingText(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = appTheme.responsiveTypography.headlineMedium.copy(
            color = MaterialTheme.colorScheme.tertiary,
            fontWeight = FontWeight.ExtraBold
        )
    )
}