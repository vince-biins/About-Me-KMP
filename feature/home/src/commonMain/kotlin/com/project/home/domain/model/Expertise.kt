package com.project.home.domain.model

data class Expertise(
    val title: String,
    val skill: List<Skill>,
    val imageUrl: String,
)

data class Skill(
    val title: String,
    val subTitle: List<String>,
)


