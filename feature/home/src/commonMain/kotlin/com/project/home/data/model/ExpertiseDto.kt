package com.project.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ExpertiseDto(
    val id: Int,
    val title: String,
    val skill: List<SkillDto>,
    val imageUrl: String? = null,
)
@Serializable
 data class SkillDto(
    val id: Int,
    val title: String,
    @SerialName("sub_skill") val subSkills: List<SubSkillDto>? = null
)

@Serializable
data class SubSkillDto(
    val title: String
)