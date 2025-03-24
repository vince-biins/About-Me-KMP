package com.project.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
actual fun getApplicationContext(): Any? {
    return LocalContext.current
}