package com.gvan.mumu.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

sealed class ViewState<out T: Any> {

    data class Loading(val isLoading: Boolean): ViewState<Nothing>()

    data class RenderSuccess<out T: Any>(val output: T): ViewState<T>()

    data class RenderFailure(val throwable: Throwable): ViewState<Nothing>()
}

suspend fun <T: Any> getViewStateFlowForNetworkCall(ioOperation: suspend() -> Flow<IOTaskResult<T>>) =
    flow {
        emit(ViewState.Loading(true))
        ioOperation().map {
            when(it) {
                is IOTaskResult.OnSuccess -> ViewState.RenderSuccess(it.data)
                is IOTaskResult.OnFailed -> ViewState.RenderFailure(it.throwable)
            }
        }.collect {
            emit(it)
        }
        emit(ViewState.Loading(false))
    }.flowOn(Dispatchers.IO)