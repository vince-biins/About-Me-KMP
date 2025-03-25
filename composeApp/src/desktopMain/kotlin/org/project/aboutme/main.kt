package org.project.aboutme

import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.compose.ui.window.singleWindowApplication
import org.jetbrains.compose.reload.DevelopmentEntryPoint

fun main() {
    singleWindowApplication(
        state = WindowState(),
        title = "AboutMe",
    ) {
        DevelopmentEntryPoint{
            App()
        }

    }
}