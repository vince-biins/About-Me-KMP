package com.project.home.data.model

import com.project.home.domain.enums.BackgroundType
import kotlinx.serialization.Serializable

@Serializable
 data class BackgroundDto(
    val id: Int,
    val headerTitle: String,
    val experience: List<ExperienceDto>
)
@Serializable
 data class ExperienceDto (
    val id: Int,
    val imageUrl: String,
    val title: String,
    val date: String,
    val description: String,
    val type: BackgroundType
)
