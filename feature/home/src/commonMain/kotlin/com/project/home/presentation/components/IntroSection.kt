package com.project.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.composables.buttons.RoundedCornerButton
import com.project.theme.appTheme

@Composable
fun IntroSection(
    modifier: Modifier = Modifier,
    superHeading: String,
    mainHeading: String,
    subHeading: String,
    onClickIntroAbout: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IntroDescription(
            modifier = Modifier.weight(1f),
            superHeading = superHeading,
            mainHeading = mainHeading,
            subHeading = subHeading,
            onClickIntroAbout = onClickIntroAbout,
        )

        IntroDescription(
            modifier = Modifier.weight(1f),
            superHeading = superHeading,
            mainHeading = mainHeading,
            subHeading = subHeading,
            onClickIntroAbout = onClickIntroAbout,
        )

    }
}

@Composable
private fun IntroDescription(
    modifier: Modifier = Modifier,
    superHeading: String,
    mainHeading: String,
    subHeading: String,
    onClickIntroAbout: () -> Unit,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            superHeading,
            style = appTheme.responsiveTypography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
            )
        )

        Text(
            mainHeading,
            style = MaterialTheme.typography.headlineLarge.copy(
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            subHeading,
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Normal
            )
        )

        RoundedCornerButton(
            onClick = onClickIntroAbout,
            text = "About Me"
        )
    }
}