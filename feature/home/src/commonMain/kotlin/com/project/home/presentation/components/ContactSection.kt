package com.project.home.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import com.project.composables.buttons.CoilImageWithError2
import com.project.home.domain.model.Contact

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContactSection(
    modifier: Modifier = Modifier,
    title: String,
    windowSize: WindowSizeClass,
    contacts: List<Contact>,
) {
    val uriHandler = LocalUriHandler.current
    val isCompact = windowSize.widthSizeClass == WindowWidthSizeClass.Compact
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.padding(
            start = 16.dp,
            end = 16.dp,
            top = 16.dp,
            bottom = 64.dp
        )
    ) {
        SectionHeadingText(
            title = title
        )

        Spacer(Modifier.height(24.dp))

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            contacts.forEach { contact ->
                Box(
                    modifier = modifier

                        .clickable {
                            uriHandler.openUri(contact.url)
                        }
                ) {
                    CoilImageWithError2(
                        imageUrl = contact.imageUrl,
                        contentScale = ContentScale.FillBounds,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

        }
    }
}
