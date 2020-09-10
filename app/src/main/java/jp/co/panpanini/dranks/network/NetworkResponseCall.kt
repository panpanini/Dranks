package jp.co.panpanini.dranks.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.NullPointerException
import java.lang.reflect.Type

class NetworkResponseCall<T>(
    private val delegate: Call<T>,
    private val successType: Type
) : Call<NetworkResponse<T>> {
    override fun enqueue(callback: Callback<NetworkResponse<T>>) {
        delegate.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                callback.onResponse(this@NetworkResponseCall, Response.success(response.toNetworkResponse()))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.onResponse(this@NetworkResponseCall, Response.success(Failure(t)))
            }
        })
    }

    override fun isExecuted(): Boolean = delegate.isExecuted

    override fun clone(): Call<NetworkResponse<T>> = NetworkResponseCall(delegate.clone(), successType)

    override fun isCanceled(): Boolean = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun execute(): Response<NetworkResponse<T>> = throw UnsupportedOperationException()

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()
}

private fun <T> Response<T>.toNetworkResponse(): NetworkResponse<T> {
    return if (!isSuccessful) {
        return ServerError(code())
    } else {
        body()?.let { Success(it) } ?: Failure(NullPointerException())
    }
}