package com.project.home.presentation.viewmodel

sealed class HomeEvent {
    data class OnHeaderClicked(val type: HeaderSectionType) : HomeEvent()
    object OnDownloadCvClicked: HomeEvent()
}

enum class HeaderSectionType(val itemPosition: Int) {
    HOME(0),
    ABOUT_ME(1),
    EXPERIENCE(2),
    EXPERTISE(3),
    CONTACT_US(4),
}