package com.project.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Serializable
data class ExpertiseDto(
    val id: Int,
    val title: String,
    val skill: List<TechSkillDto>,
    val imageUrl: String? = null,
)

@Serializable
data class TechSkillDto(
    val id: Int,
    val title: String,
    val imageUrl: String,
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