package com.project.home.domain.model

data class Expertise(
    val title: String,
    val skill: List<TechSkill>,
    val imageUrl: String,
)

data class Skill(
    val title: String,
    val subTitle: List<String>,
)

data class TechSkill(
    val imageUrl: String,
    val title: String,
)


