package com.nbespalovv.utils

import retrofit2.Response


sealed class DataState<out T> {
    class Success<out T>(val data: T) : DataState<T>()

    data object EmptyBody: DataState<Nothing>()
    class Failure(val reason: String): DataState<Nothing>()
}

fun <T> Response<T>.toDataState(): DataState<T> {
    if (this.isSuccessful) {
        val body = this.body() ?: return DataState.EmptyBody
        return DataState.Success(body)
    } else {
        return DataState.Failure(reason = this.message())
    }
}

inline fun <T> DataState<T>.handleState(onFailure: (String) -> Unit = {}, onSuccess: (T) -> Unit = {}): DataState<T> {
    when(this) {
        is DataState.Success -> onSuccess(this.data)
        is DataState.Failure -> onFailure(this.reason)
        DataState.EmptyBody -> {}
    }
    return this
}

fun <T> DataState<T>.asUnit(): DataState<Unit> =
    when(this) {
        is DataState.Success -> DataState.Success(Unit)
        is DataState.Failure -> DataState.Failure(this.reason)
        DataState.EmptyBody -> DataState.EmptyBody
    }
