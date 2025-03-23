package com.project.home.data.model

import com.project.home.domain.enums.BackgroundType

 data class BackgroundDto(
    val headerTitle: String,
    val experience: List<ExperienceDto>
)

 data class ExperienceDto (
    val imageUrl: String,
    val title: String,
    val date: String,
    val description: String,
    val type: BackgroundType
)
