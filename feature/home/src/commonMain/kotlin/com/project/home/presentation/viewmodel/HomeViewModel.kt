package com.project.home.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.project.composables.buttons.BaseViewModel
import com.project.home.domain.model.Profile
import com.project.home.domain.repository.HomeRepository
import com.project.utils.platform.saveFile
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeRepository: HomeRepository,

): BaseViewModel() {


    private val _isRefreshing = MutableStateFlow(false)

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event  = _event.asSharedFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            fetchHomeState()
        }
    }

    private suspend fun fetchHomeState() {
        try {
            val profileFlow = combine(
                safeFlow({ homeRepository.getBasicProfileData() },null),
                safeFlow({ homeRepository.getDetailProfileData() }, null),
                safeFlow({ homeRepository.getBackgroundData() }, emptyList()),
                safeFlow({ homeRepository.getExpertiseData() }, emptyList()),
                safeFlow({ homeRepository.getContactData() }, emptyList()),

            ) { basic, detail, background, skills, contact, ->
                Profile(
                    basicProfile = basic,
                    detailedProfile = detail,
                    background = background,
                    skills = skills,
                    contact = contact)
            }
            profileFlow.collect { profile ->
                _state.update { it.copy(profile = profile, isLoading = false,isRefreshing = false) }
            }
        } catch (e: Exception) {
            _state.update { it.copy(error = e.message, isLoading = false,isRefreshing = false) }
        }
    }

    fun onEvent(uiEvent: HomeEvent ) {
        when(uiEvent) {
            is HomeEvent.OnHeaderClicked -> {
                viewModelScope.launch {
                    _event.emit(uiEvent)
                }
            }

            is HomeEvent.OnDownloadCvClicked -> {
                viewModelScope.launch {
                    val file = homeRepository.downloadCv(uiEvent.url)
                    file?.let {
                        saveFile(FILE_NAME, data = it)
                    }
                }
            }

            is HomeEvent.OnRefresh -> {
                _isRefreshing.update { true }
                viewModelScope.launch {
                    try {
                        _state.update { it.copy(isLoading = true) }
                        fetchHomeState()
                    } catch (e: Exception) {
                        // Handle any errors and propagate them to the event flow
                        // _event.emit(HomeEvent.ShowError(e.message))
                    } finally {

                        _isRefreshing.update { false }
                    }
                }
                _state.update { it.copy(isRefreshing = true, isLoading = true) }
            }
        }
    }

    companion object {
        private val FILE_NAME = "Ola_ola_Vincent_CV.pdf"
    }
}

