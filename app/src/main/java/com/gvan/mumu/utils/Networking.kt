package com.gvan.mumu.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

typealias NetworkAPIInvoke<T> = suspend () -> Response<T>

sealed class IOTaskResult<out DTO: Any> {
    data class OnSuccess<out DTO : Any>(val data: DTO) : IOTaskResult<DTO>()
    data class OnFailed(val throwable: Throwable) : IOTaskResult<Nothing>()
}

suspend fun<T: Any> performSafeNetworkApiCall(
    networkApiCall: NetworkAPIInvoke<T>
): Flow<IOTaskResult<T>> {
    return flow{
        val response = networkApiCall()
        if(response.isSuccessful) {
            response.body()?.let {
                emit(IOTaskResult.OnSuccess(it))
            }
                ?: emit(IOTaskResult.OnFailed(IOException("API call successful but empty response body")))
            return@flow
        }
        emit(
            IOTaskResult.OnFailed(
                IOException(
                    "API call failed with error - ${response.errorBody()?.string() ?: "Network error"}"
                )
            )
        )
        return@flow
    }.catch { e ->
        emit(IOTaskResult.OnFailed(IOException("Exception during network API call: ${e.message}")))
        return@catch
    }.flowOn(Dispatchers.IO)
}