package com.project.home.presentation.components

import aboutme.feature.home.generated.resources.Res
import aboutme.feature.home.generated.resources.menu
import aboutme.feature.home.generated.resources.vmo_logo_nobg
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.unit.dp
import com.project.theme.appTheme
import kotlinx.coroutines.delay
import org.jetbrains.compose.resources.painterResource

@Composable
fun CustomSplashScreen(onTimeout: () -> Unit) {

    LaunchedEffect(Unit) {
        delay(2000)
        onTimeout()
    }
    Box(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 32.dp)
        ) {

            Spacer(modifier = Modifier.weight(1f))

            Image(
                painter = painterResource(Res.drawable.vmo_logo_nobg),
                contentDescription = "App Logo",
                modifier = Modifier.size(240.dp)
            )

            Spacer(modifier = Modifier.weight(1f))


            Text(
                text = "Vince-biins",
                style = appTheme.responsiveTypography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.outline
                )
            )
        }
    }
}