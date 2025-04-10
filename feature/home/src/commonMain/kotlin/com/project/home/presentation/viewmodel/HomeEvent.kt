package com.project.home.presentation.viewmodel

sealed class HomeEvent {
    data class OnHeaderClicked(val type: HeaderSectionType) : HomeEvent()
    data class OnDownloadCvClicked(val url: String): HomeEvent()
    object OnRefresh: HomeEvent()
    object OnNavigationDrawerClicked: HomeEvent()
}

enum class HeaderSectionType(val itemPosition: Int, val text: String) {
    HOME(0, "Home"),
    ABOUT_ME(1, "About"),
    EXPERIENCE(2, "Experience"),
    EXPERTISE(3,"Skills"),
    PROJECT(4, "Project"),
    CONTACT_US(5, "Contact"),
}