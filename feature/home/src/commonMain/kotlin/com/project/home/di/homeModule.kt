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