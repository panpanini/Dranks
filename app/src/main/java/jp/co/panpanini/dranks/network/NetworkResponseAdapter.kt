package jp.co.panpanini.dranks.network

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

private class NetworkResponseAdapter<T>(
    private val successType: Type
) : CallAdapter<T, Call<NetworkResponse<T>>> {
    override fun responseType(): Type = successType

    override fun adapt(call: Call<T>): Call<NetworkResponse<T>> =
        NetworkResponseCall(call, successType)
}

class NetworkResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) return null
        check(returnType is ParameterizedType) { "Return type must be a parameterized type." }

        val responseType = getParameterUpperBound(0, returnType)
        if (getRawType(responseType) != NetworkResponse::class.java) return null
        check(responseType is ParameterizedType) { "Response type must be a parameterized type." }

        val successType = getParameterUpperBound(0, responseType)
        return NetworkResponseAdapter<Any>(successType)
    }

}