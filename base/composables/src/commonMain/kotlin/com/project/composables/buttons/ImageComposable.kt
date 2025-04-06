package com.project.composables.buttons

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter

@Composable
fun CoilImageWithError2(
    modifier: Modifier = Modifier,
    imageUrl: String,

) {
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
    )
    println("$imageUrl - ${painter.state.value}")
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        when (painter.state.collectAsState().value) {
            is AsyncImagePainter.State.Loading -> {
                Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(Modifier.size(24.dp))
            }
            }
            is AsyncImagePainter.State.Error -> {
                println("Error ${(painter.state.value as AsyncImagePainter.State.Error).result.throwable}")
                Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Warning,
                    contentDescription = "Error loading image",
                    tint = MaterialTheme.colorScheme.error
                )
            }
            }
            is AsyncImagePainter.State.Success -> {
                Image(painter = painter, contentDescription = null,
                    modifier = modifier,
                    contentScale = ContentScale.FillBounds,
                )
            }
            else ->{}
        }
    }
}



