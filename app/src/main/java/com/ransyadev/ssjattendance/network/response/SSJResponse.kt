package com.ransyadev.ssjattendance.network.response

sealed interface SSJResponse<out T>  {
    object Loading : SSJResponse<Nothing>

    open class Error(
        open val message: String,
        val meta: Map<String, Any?> = mapOf(),
    ) : SSJResponse<Nothing>

    object Empty : SSJResponse<Nothing>

    class Success<T>(
        val data: T,
        val meta: Map<String, Any?> = mapOf(),
    ) : SSJResponse<T>
}
