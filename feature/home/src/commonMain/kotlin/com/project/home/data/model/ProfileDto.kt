package com.project.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames


@Serializable
data class BasicProfileDto(
    @SerialName("superTitle") val headerSuperTitle: String,
    @SerialName("title") val headerTitle: String,
    @SerialName("subTitle") val headerSubtitle: String,
    @SerialName("imageUrl") val headerImageUrl: String,
)

 data class DetailedProfileDto(
    val title: String,
    val description: String,
    val name: String,
    val age: Int,
    val location: String,
    val email: String,
)
@Serializable
data class Profile2(
    val imageUrl: String,
    val subTitle: String,
    val superTitle: String,
    val title: String,

    )