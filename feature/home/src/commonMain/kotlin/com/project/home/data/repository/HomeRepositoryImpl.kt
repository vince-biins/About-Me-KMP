package com.project.home.data.repository

import com.project.home.data.Mock
import com.project.home.data.mappers.transform
import com.project.home.domain.model.Profile
import com.project.home.domain.repository.HomeRepository

class HomeRepositoryImpl: HomeRepository {
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
}