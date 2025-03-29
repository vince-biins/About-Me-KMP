package com.project.home.domain.service

import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.KSerializer

interface DatabaseService {
    fun <T> get(path: String, serializer: KSerializer<T>): Flow<T?>
}