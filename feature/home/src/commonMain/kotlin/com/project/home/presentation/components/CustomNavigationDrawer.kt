package com.project.home.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.project.home.presentation.viewmodel.HeaderSectionType

@Composable
fun CustomNavigationDrawer(
    modifier: Modifier = Modifier,
    drawerState: DrawerState,
    gestureEnable: Boolean,
    sectionType: List<HeaderSectionType>,
    onSectionClicked: (HeaderSectionType) -> Unit,
    content: @Composable () -> Unit
) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    sectionType.forEach { section ->
                        TextHeaderClickable(
                            modifier = Modifier.fillMaxWidth().align(Alignment.Start),
                            text = section.text,
                            onClick = {
                                onSectionClicked(section)
                            }
                        )
                    }
                }
            }

        },
        modifier = modifier,
        content = content,
        gesturesEnabled = gestureEnable,
    )
}
