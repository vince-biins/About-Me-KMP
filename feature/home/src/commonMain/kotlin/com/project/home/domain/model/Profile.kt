package com.project.home.domain.model

data class Profile(
    val basicProfile: BasicProfile?,
    val detailedProfile: DetailedProfile?,
    val background: List<Background>,
    val skills: List<Expertise>,
    val contact: List<Contact>,
    val projects: List<Project>,
)

data class BasicProfile(
    val headerTitle: String,
    val headerSubtitle: String,
    val headerImageUrl: String,
    val headerSuperTItle: String,
)


data class DetailedProfile(
    val title: String,
    val description: String,
    val name: String,
    val age: Int,
    val location: String,
    val email: String,
    val fileUrl: String,
)
