package com.project.home.data.service

import com.project.home.data.model.BackgroundDto
import com.project.home.data.model.BasicProfileDto
import com.project.home.data.model.ContactDto
import com.project.home.data.model.DetailedProfileDto
import com.project.home.data.model.ExpertiseDto
import com.project.home.domain.service.DatabaseService

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SupabaseDatabaseService : DatabaseService {
    private val  supabase = createSupabaseClient(
    supabaseUrl = "https://plkbcgxivydhwnebkzhw.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBsa2JjZ3hpdnlkaHduZWJremh3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDM0NzU3NjksImV4cCI6MjA1OTA1MTc2OX0.GU0-6o9p595b0veuU6G6pj5UeTpsKag0o4rICymw4SI"
    ) {
        install(Postgrest)

    }
    override fun getContact(path: String): Flow<List<ContactDto>> = flow {
        try {
            val res = supabase.from(path).select().decodeList<ContactDto>()
            emit(res)
        } catch (e: Exception) {
            println(e.message)
            emit(emptyList())
        }
    }

    override fun getIntroProfile(path: String): Flow<BasicProfileDto?> = flow {

        try {
            val res = supabase.from(path).select().decodeSingle<BasicProfileDto>()

            emit(res)
        } catch (e: Exception) {
            println(e.message)
            emit(null)
        }
    }

    override fun getDetailProfile(path: String): Flow<DetailedProfileDto?> = flow {

        try {
            val res = supabase.from(path).select().decodeSingle<DetailedProfileDto>()
            emit(res)
        } catch (e: Exception) {
            println(e.message)
            emit(null)
        }
    }

    override fun getExpertise(path: String): Flow<List<ExpertiseDto>> = flow {

        try {
            val res = supabase.from(path)
                .select(Columns.raw("""
                    *,
                    skill(*,sub_skill(title))
                """.trimIndent())
                ).decodeList<ExpertiseDto>()
            emit(res)
        } catch (e: Exception) {
            println(e.message)
            emit(emptyList())
        }
    }

    override fun getExperience(path: String): Flow<List<BackgroundDto>> = flow {

        try {
            val res = supabase.from(path).select(
                Columns.raw(
                    "*,experience(*)"
                )
            ).decodeList<BackgroundDto>()
            emit(res)
        } catch (e: Exception) {
            println(e.message)
            emit(emptyList())
        }
    }

}