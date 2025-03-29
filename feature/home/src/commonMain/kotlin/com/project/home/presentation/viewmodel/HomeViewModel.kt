package com.project.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.project.home.domain.repository.HomeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            fetchHomeState()
            delay(500)
            getProfileData()
        }
    }

     private fun fetchHomeState() {
        viewModelScope.launch {
            try {
                val profile = homeRepository.fetchProfileData()
                delay(2000)
                _state.update { it.copy(profile = profile, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }

    private fun getProfileData() {
        viewModelScope.launch {
            try {
                val databaseResult = homeRepository.getProfileData()
                databaseResult.collectLatest { profile ->
                    println("VINCENT -viewmodel- $profile")
                    _state.update { currentState ->
                        currentState.profile?.let { currentProfile ->
                            currentState.copy(
                                profile = currentProfile.copy(basicProfile = profile ?: currentProfile.basicProfile),
                                isLoading = false,
                                error = null
                            )
                        } ?: currentState.copy(isLoading = false, error = "Profile data is not available")

                    }
                }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }
}