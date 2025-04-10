package com.project.home.data.repository

import com.project.home.data.mappers.transform
import com.project.home.domain.model.Background
import com.project.home.domain.model.BasicProfile
import com.project.home.domain.model.Contact
import com.project.home.domain.model.DetailedProfile
import com.project.home.domain.model.Expertise
import com.project.home.domain.model.Project
import com.project.home.domain.repository.HomeRepository
import com.project.home.domain.service.DatabaseService
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsChannel
import io.ktor.utils.io.toByteArray
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class HomeRepositoryImpl(
    private val supabaseDatabaseService: DatabaseService,
    private val client: HttpClient,
) : HomeRepository {

    override suspend fun getBasicProfileData(): Flow<BasicProfile?> =
        supabaseDatabaseService.getIntroProfile(INTRO_PROFILE_PATH).map {
            it?.transform()
        }

    override suspend fun getDetailProfileData(): Flow<DetailedProfile?> =
        supabaseDatabaseService.getDetailProfile(DETAIL_PROFILE_PATH).map {
            it?.transform()
        }

    override suspend fun getExpertiseData(): Flow<List<Expertise>> =
        supabaseDatabaseService.getExpertise(EXPERTISE_PATH).map { expertise ->
            expertise.map { it.transform() }
        }

    override suspend fun getBackgroundData(): Flow<List<Background>> =
        supabaseDatabaseService.getExperience(EXPERIENCE_PATH_PATH).map { experience ->
            experience.map { it.transform() }
        }

    override suspend fun getContactData(): Flow<List<Contact>> =
        supabaseDatabaseService.getContact(CONTACT_PATH).map { contact ->
            contact.map { it.transform() }
        }

    override suspend fun getProjects(): Flow<List<Project>>  =
        supabaseDatabaseService.getProject(PROJECT_PATH).map { project ->
            project.map { it.transform() }
    }

    override suspend fun downloadCv(url: String): ByteArray? {
        try {
            val response: HttpResponse = client.get(url)
            return response.bodyAsChannel().toByteArray()
        } catch (e: Exception) {
            return null
        }
    }


    companion object {
        val PROFILE_PATH = "profile"
        val INTRO_PROFILE_PATH = "intro_profile"
        val DETAIL_PROFILE_PATH = "detail_profile"
        val EXPERIENCE_PATH_PATH = "background"
        val EXPERTISE_PATH = "expertise"
        val CONTACT_PATH = "contact"
        val PROJECT_PATH = "project"
    }
}
