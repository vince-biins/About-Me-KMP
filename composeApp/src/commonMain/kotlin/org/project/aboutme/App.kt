package org.project.aboutme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.project.aboutme.base.theme.AboutMeTheme

@Composable
fun App() {
    AboutMeTheme(darkTheme = true) {
        Column(
            modifier = Modifier.padding(16.dp).fillMaxSize().background(
                MaterialTheme.colorScheme.background
            )
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
            Button(onClick = { /* Handle click */ }) {
                Text("Click Me")
            }
        }
    }
}