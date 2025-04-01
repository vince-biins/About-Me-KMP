package com.project.home.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonNames


@Serializable
@SerialName("intro_profile")
data class BasicProfileDto(
    @SerialName("superTitle") val headerSuperTitle: String,
    @SerialName("title") val headerTitle: String,
    @SerialName("subTitle") val headerSubtitle: String,
    @SerialName("imageUrl") val headerImageUrl: String,
)

@Serializable
 data class DetailedProfileDto(
    val title: String,
    val description: String,
    val name: String,
    val birthday: String,
    val location: String,
    val email: String,
)
