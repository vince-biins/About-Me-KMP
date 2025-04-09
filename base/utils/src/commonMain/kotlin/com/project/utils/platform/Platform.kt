package com.project.utils.platform

expect fun platformName(): Platform

enum class Platform {
    ANDROID, DESKTOP, WEB, IOS
}
