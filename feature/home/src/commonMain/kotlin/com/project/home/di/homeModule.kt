package com.project.home.di

import com.project.home.config.SupabaseConfig
import com.project.home.data.repository.HomeRepositoryImpl
import com.project.home.data.service.SupabaseDatabaseService
import com.project.home.domain.repository.HomeRepository
import com.project.home.domain.service.DatabaseService
import com.project.home.presentation.viewmodel.HomeViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    single<HttpClient> {
        HttpClient()
    }
    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = SupabaseConfig.SUPABASE_URL,
            supabaseKey = SupabaseConfig.SUPABASE_API_KEY
        ) {
            install(Postgrest)
        }
    }
    single<DatabaseService> {
        SupabaseDatabaseService(
            supabase = get<SupabaseClient>()
        )
    }
    single<HomeRepository> {
        HomeRepositoryImpl(
            supabaseDatabaseService = get(),
            client = get()
        )
    }
    viewModel {
        HomeViewModel(
            homeRepository = get(),
        )
    }
}