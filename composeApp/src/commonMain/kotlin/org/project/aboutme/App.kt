package org.project.aboutme

import aboutme.composeapp.generated.resources.Res
import aboutme.composeapp.generated.resources.compose_multiplatform
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.composables.buttons.RoundedCornerButton
import com.project.theme.AboutMeTheme

@Composable
fun App() {
    AboutMeTheme {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize()
        ) {
            Text(
                text = "This is a Headline Large",

                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "This is a Body Medium",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "This is a Title Small",
                style = MaterialTheme.typography.titleSmall,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            RoundedCornerButton(
                text = "Email",
                onClick = {},
                suffixIcon = Res.drawable.compose_multiplatform,
                iconTint = MaterialTheme.colorScheme.tertiary,
               backgroundColor =  MaterialTheme.colorScheme.background
            )
        }
    }
}

