package com.project.home.di

import com.project.home.data.repository.HomeRepositoryImpl
import com.project.home.data.service.FirebaseDatabaseServiceImpl
import com.project.home.domain.repository.HomeRepository
import com.project.home.domain.service.DatabaseService
import com.project.home.presentation.viewmodel.HomeViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {

    single<DatabaseService> { FirebaseDatabaseServiceImpl() }
    single <HomeRepository>{
        HomeRepositoryImpl(
            databaseService = get()
        )
    }

    viewModel {
        HomeViewModel(get())
    }
}