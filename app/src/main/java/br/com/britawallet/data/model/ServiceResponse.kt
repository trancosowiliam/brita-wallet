package br.com.britawallet.data.model

sealed class ServiceResponse<out T> {
    object OK : ServiceResponse<Nothing>()
    class BODY<out T>(val value: T) : ServiceResponse<T>()
    class ERROR(val message: String = "") : ServiceResponse<Nothing>()
}

fun <T> ServiceResponse<T>.whenever(
    isBody: (T) -> Unit = {},
    isError: (String) -> Unit = {},
    isOk: () -> Unit = {}
) {
    when (this) {
        is ServiceResponse.BODY<T> -> isBody(this.value)
        is ServiceResponse.ERROR -> isError(this.message)
        is ServiceResponse.OK -> isOk
    }
}

fun <T> ServiceResponse<T?>.withBody(action: (T) -> Unit = {}) {
    (this as? ServiceResponse.BODY<T>)?.value?.let(action)
}

fun <T> ServiceResponse<T>.getBodyOrNull(): T? {
    return when (this) {
        is ServiceResponse.BODY<T> -> this.value
        is ServiceResponse.ERROR -> null
        is ServiceResponse.OK -> null
    }
}

fun <T> T.toServiceBody(): ServiceResponse.BODY<T> {
    return ServiceResponse.BODY(this)
}