package com.project.home.di

import com.project.home.data.repository.HomeRepositoryImpl

import com.project.home.data.service.SupabaseDatabaseService
import com.project.home.domain.repository.HomeRepository
import com.project.home.domain.service.DatabaseService

import com.project.home.presentation.viewmodel.HomeViewModel
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<DatabaseService> {
        SupabaseDatabaseService(
            supabase = createSupabaseClient(
                supabaseUrl = "https://plkbcgxivydhwnebkzhw.supabase.co",
                supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InBsa2JjZ3hpdnlkaHduZWJremh3Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDM0NzU3NjksImV4cCI6MjA1OTA1MTc2OX0.GU0-6o9p595b0veuU6G6pj5UeTpsKag0o4rICymw4SI"
            ) {
                install(Postgrest)
            }
        )
    }
    single<HomeRepository> {
        HomeRepositoryImpl(
            supabaseDatabaseService = get(),
        )
    }

    viewModel {
        HomeViewModel(get())
    }
}