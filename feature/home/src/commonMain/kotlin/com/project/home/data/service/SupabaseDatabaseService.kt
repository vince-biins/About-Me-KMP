package com.project.home.data.service
import com.project.home.data.model.BackgroundDto
import com.project.home.data.model.BasicProfileDto
import com.project.home.data.model.ContactDto
import com.project.home.data.model.DetailedProfileDto
import com.project.home.data.model.ExpertiseDto
import com.project.home.data.model.ProjectDto
import com.project.home.domain.service.DatabaseService
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SupabaseDatabaseService(
    private val  supabase: SupabaseClient
) : DatabaseService {

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
                    skill(*)
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

    override fun getProject(path: String): Flow<List<ProjectDto>> = flow {
        try {
            val res = supabase.from(path).select {
                order(column = "priority", order = Order.DESCENDING)
            }.decodeList<ProjectDto>()
            println(res)
            emit(res)
        } catch (e: Exception) {
            println(e.message)
            emit(emptyList())
        }
    }

}