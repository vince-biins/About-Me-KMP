package com.project.home.presentation.viewmodel

import com.project.home.domain.model.Profile

data class HomeState(
    val isLoading: Boolean = true,
    val profile: Profile? = null,
    val error: String? = null
)
