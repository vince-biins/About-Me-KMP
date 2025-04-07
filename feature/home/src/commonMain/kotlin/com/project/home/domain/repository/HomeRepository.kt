package com.project.home.domain.repository

import com.project.home.domain.model.Background
import com.project.home.domain.model.BasicProfile
import com.project.home.domain.model.Contact
import com.project.home.domain.model.DetailedProfile
import com.project.home.domain.model.Expertise
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    suspend fun getBasicProfileData(): Flow<BasicProfile?>
    suspend fun getDetailProfileData(): Flow<DetailedProfile?>
    suspend fun getExpertiseData(): Flow<List<Expertise>>
    suspend fun getBackgroundData(): Flow<List<Background>>
    suspend fun getContactData(): Flow<List<Contact>>
    suspend fun downloadCv(url: String): ByteArray?
}