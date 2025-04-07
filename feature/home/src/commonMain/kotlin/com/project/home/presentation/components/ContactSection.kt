package com.project.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.project.home.domain.model.Contact
import com.project.theme.appTheme

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
            horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            contacts.forEach { contact ->
                ContactTile(
                    text = contact.title,
                    onContactClicked = {
                        uriHandler.openUri(it)
                    },
                    isCompact = isCompact,
                    url = contact.url
                )
            }

        }
    }
}

@Composable
private fun ContactTile(
    isCompact: Boolean,
    text: String,
    url: String,
    modifier: Modifier = Modifier,
    onContactClicked: (String) -> Unit,
) {
    val paddingHorizontal = if (isCompact) 16.dp else 24.dp
    val width = if(isCompact) 180.dp else 200.dp
    Row(
        modifier = modifier
            .width(width)
            .clickable {
                onContactClicked(url)
            }
            .background(
                MaterialTheme.colorScheme.secondary,
                shape = RoundedCornerShape(4.dp)
            )
            .padding(horizontal = paddingHorizontal, vertical = 8.dp),

        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            style = appTheme.responsiveTypography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )

        Box {
            Icon(
                Icons.Filled.Call,
                "",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .size(if(isCompact) 24.dp else 32.dp)
            )
        }
    }
}