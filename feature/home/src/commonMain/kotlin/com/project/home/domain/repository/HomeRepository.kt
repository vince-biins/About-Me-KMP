package com.project.home.domain.repository

import com.project.home.domain.model.BasicProfile
import com.project.home.domain.model.Profile
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun fetchProfileData() : Profile
    suspend fun getProfileData(): Flow<BasicProfile?>
}