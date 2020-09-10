package jp.co.panpanini.dranks.network

import java.lang.Exception

sealed class NetworkResponse<T>

data class Success<T>(val data: T) : NetworkResponse<T>()
data class ServerError<T>(val httpCode: Int) : NetworkResponse<T>()
data class Failure<T>(val error: Throwable) : NetworkResponse<T>()