package com.project.home.data.model

import kotlinx.serialization.Serializable

@Serializable
 data class ContactDto(
    val title: String,
    val url: String,
     val imageUrl: String,
)
