package com.project.home.data.repository

import com.project.home.data.Mock
import com.project.home.data.mappers.transform
import com.project.home.data.model.BasicProfileDto
import com.project.home.domain.model.BasicProfile
import com.project.home.domain.model.Profile
import com.project.home.domain.repository.HomeRepository
import com.project.home.domain.service.DatabaseService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable

class HomeRepositoryImpl(
    private val databaseService: DatabaseService
): HomeRepository {
    override suspend fun fetchProfileData(): Profile {
        val shouldThrowError = false
        when (shouldThrowError) {
            true -> throw Exception("Something went wrong")
            false -> return Profile(
                basicProfile = Mock.basicProfile.transform(),
                detailedProfile = Mock.detailedProfile.transform(),
                background = Mock.background.map { it.transform() },
                skills = Mock.expertise.map { it.transform() },
                contact = Mock.contactDtos.map { it.transform() },
            )
        }
    }

    override suspend fun getProfileData(): Flow<BasicProfile?> {
        val res = databaseService.get("profile/intro_profile", BasicProfileDto.serializer())
        return res.map { it?.transform() }
    }
}
