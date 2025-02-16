package com.nbespalovv.utils


sealed class DataState<out T> {
    class Success<out T>(val data: T) : DataState<T>()

    data object EmptyBody: DataState<Nothing>()
    class Failure(val reason: String): DataState<Nothing>()
}
