package com.project.composables.buttons

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow

abstract class BaseViewModel: ViewModel() {
    protected fun <T> safeFlow(
        block: suspend () -> Flow<T>,
        defaultValue: T
    ): Flow<T> = flow {
        try {
            emitAll(block())
        } catch (e: Exception) {
            emit(defaultValue)
        }
    }
}