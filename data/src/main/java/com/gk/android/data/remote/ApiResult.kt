package com.gk.android.data.remote

import com.apollographql.apollo.ApolloQueryCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.apollographql.apollo.exception.ApolloException
import com.gk.android.domain.Resource

sealed class ApiResult<T> {
    data class Success<T>(val data: T) : ApiResult<T>()
    data class ApiError<T>(val error: Error?) : ApiResult<T>()
    data class NetworkError<T>(val exception: Exception) : ApiResult<T>()

    fun <R> toResource(transformSuccess: (data: T) -> R): Resource<R> = when (this) {
        is Success -> Resource.Success(transformSuccess(data))
        is ApiError -> Resource.ApiError(this.error?.message ?: "API ERROR")
        is NetworkError -> Resource.Error(this.exception.message ?: "NETWORK ERROR")
    }
}

@Suppress("UNCHECKED_CAST")
internal suspend fun <T> apiCall(apolloCall: () -> ApolloQueryCall<T>): ApiResult<T> {
    val response = try {
        apolloCall().await()
    } catch (e: ApolloException) {
        return ApiResult.NetworkError(e)
    }

    val data = response.data
    return if (data == null || response.hasErrors()) {
        ApiResult.ApiError(parseApiError(response))
    } else {
        ApiResult.Success(data)
    }
}

private fun parseApiError(response: Response<*>): Error? {
    val errorBody = response.errors?.toString()
    if (errorBody == null || errorBody.isEmpty()) return null
    return Error(errorBody)
}