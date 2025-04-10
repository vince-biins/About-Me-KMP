package com.project.home.presentation.components

import aboutme.feature.home.generated.resources.Res
import aboutme.feature.home.generated.resources.menu
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.List
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.project.home.presentation.viewmodel.HeaderSectionType
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeaderSection(
    windowSize: WindowSizeClass,
    modifier: Modifier = Modifier,
    section: List<HeaderSectionType>,
    onSectionClicked: (HeaderSectionType) -> Unit,
    onDrawerClicked: () -> Unit,
) {
    val isCompact = windowSize.widthSizeClass == WindowWidthSizeClass.Compact
    if(isCompact) {
        Box(
            modifier = modifier
        ){
            IconButton(
                onClick = onDrawerClicked,
            ) {
                Icon(
                    painter = painterResource( Res.drawable.menu),
                    modifier = Modifier.size(24.dp),
                    contentDescription = "More",
                    tint = Color.White
                )
            }
        }

    }
    else {
        Box(
            modifier = modifier,
        ) {

            FlowRow(
                modifier = modifier.fillMaxWidth().padding(8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalArrangement = Arrangement.Center
            ) {
                section.forEach {
                    TextHeaderClickable(
                        text = it.text,
                        onClick = {
                            onSectionClicked(it)
                        }
                    )
                }
            }
        }
    }

}

@Composable
fun TextHeaderClickable(
    modifier: Modifier = Modifier,
    text: String,
    shape: Shape = RectangleShape,
    onClick: () -> Unit
) {

    TextButton(
        modifier = modifier,
        onClick = onClick,
        shape = shape
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = MaterialTheme.colorScheme.onSurface
            ),
        )
    }
}
