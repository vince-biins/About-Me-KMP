package com.project.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HeaderSection(
    modifier: Modifier = Modifier,
    onHomeClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onExperienceClicked: () -> Unit,
    onSkillsClicked:() -> Unit,
    onContactClicked:() -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth().padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        TextHeaderClickable(
            text = "Home",
            onClick = onHomeClicked
        )
        TextHeaderClickable(
            text = "About",
            onClick = onAboutClicked
        )
        TextHeaderClickable(
            text = "Experience",
            onClick = onExperienceClicked
        )
        TextHeaderClickable(
            text = "Skills",
            onClick = onSkillsClicked
        )
        TextHeaderClickable(
            text = "Contact",
            onClick = onContactClicked
        )

    }
}

@Composable
fun TextHeaderClickable(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {

    TextButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}
