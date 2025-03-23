package com.project.home.data.model


data class BasicProfileDto(
    val headerTitle: String,
    val headerSubtitle: String,
    val headerImageUrl: String,
)

 data class DetailedProfileDto(
    val title: String,
    val description: String,
    val name: String,
    val age: Int,
    val location: String,
    val email: String,
)
