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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage

@Composable
fun CoilImageWithError(
    modifier: Modifier = Modifier,
    imageUrl: String,
    imageOptions: ImageOptions = ImageOptions(
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.Center
    )
) {
    CoilImage(
        modifier = modifier,
        imageModel = { imageUrl },
        imageOptions = imageOptions,
        loading = {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.background),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(Modifier.size(24.dp))
            }
        },
        failure = {
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
    )
}

@Composable
fun CoilImageWithError2(
    modifier: Modifier = Modifier,
    imageUrl: String,

) {
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
    )

    Image(
        painter = painter,
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        alignment = Alignment.Center,
        modifier = modifier
    )
}



