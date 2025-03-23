package com.project.home.data.model

 data class ExpertiseDto(
    val title: String,
    val skill: List<SkillDto>,
    val imageUrl: String,
)

 data class SkillDto(
    val title: String,
    val subTitle: List<String>,
)


