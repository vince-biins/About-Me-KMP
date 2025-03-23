package com.project.home.domain.repository

import com.project.home.domain.model.Profile

interface HomeRepository {
    suspend fun fetchProfileData() : Profile
}