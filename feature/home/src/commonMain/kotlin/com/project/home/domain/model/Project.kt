package com.project.home.domain.model

data class Project(
    val title: String,
    val description: String,
    val appUrl: String,
    val imageUrl: String,
    val stack: List<String>
)
