package com.project.home.data.model

import kotlinx.serialization.Serializable


@Serializable
data class ProjectDto(
    val id: Int,
    val title: String,
    val description: String,
    val url: String?,
    val imageUrl: String?,
    val priority: Int,
    val stack: String,
)
