package com.project.composables.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource


@Composable
fun RoundedCornerButton(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    shape: Shape = RoundedCornerShape(16),
    modifier: Modifier = Modifier,
    suffixIcon: DrawableResource? = null,
    iconTint: Color = Color.Unspecified,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit,

    ) {

    Button(
        onClick = onClick,
        shape = shape,
        modifier = modifier
            .wrapContentWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clip(shape)
            .background(backgroundColor)


    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge.copy(
                color = Color.White,
                fontWeight = fontWeight,
                letterSpacing = 2.sp
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis

        )

        if (suffixIcon != null) {
            Icon(
                modifier = Modifier.size(50.dp),
                painter = painterResource(suffixIcon),
                tint = iconTint,
                contentDescription = null
            )
        }
    }
}