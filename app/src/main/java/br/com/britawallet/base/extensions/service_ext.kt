package br.com.britawallet.base.extensions

import br.com.britawallet.data.model.ServiceResponse
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.suspendCancellableCoroutine
import okhttp3.Request
import okio.Buffer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.util.*
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.text.Charsets.UTF_8

suspend fun <T : Any> Call<T>.awaitResult(): ServiceResponse<T> {
    return suspendCancellableCoroutine { continuation ->
        enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>?, response: Response<T>) {
                continuation.resume(
                    if (response.isSuccessful) {
                        val body = response.body()
                        if (body == null) {
                            ServiceResponse.ERROR("Response body is null")
                        } else {
                            ServiceResponse.BODY(body)
                        }
                    } else {
                        ServiceResponse.ERROR(response.message())
                    }
                )
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                if (continuation.isCancelled) return
                continuation.resume(ServiceResponse.ERROR(t.message ?: "Erro inesperado"))
            }
        })

        registerOnCompletion(continuation)
    }
}

private fun Call<*>.registerOnCompletion(continuation: CancellableContinuation<*>) {
    continuation.invokeOnCancellation {
        try {
            cancel()
        } catch (ex: Throwable) {
        }
    }
}

fun Request.toCurl(): String {
    val method = this.method().toUpperCase(Locale.US)
    val headers =
        headers().names().joinToString(separator = "") { "-H \"$it: {{$it}}\" " }
    val body = body()?.let { requestBody ->
        val buffer = Buffer()
        requestBody.writeTo(buffer)

        requestBody.contentType()?.let { contentType ->
            "-H \"Content-Type: $contentType\" -d '${buffer.readString(UTF_8)}'"
        }
    } ?: ""

    return "curl -X $method $headers$body \"${url()}\" -L"
}