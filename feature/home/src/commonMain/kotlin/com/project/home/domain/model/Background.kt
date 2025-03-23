package com.project.home.domain.model

import com.project.home.domain.enums.BackgroundType

data class Background(
    val headerTitle: String,
    val experience: List<Experience>
)

data class Experience (
    val imageUrl: String,
    val title: String,
    val date: String,
    val description: String,
    val type: BackgroundType
)
