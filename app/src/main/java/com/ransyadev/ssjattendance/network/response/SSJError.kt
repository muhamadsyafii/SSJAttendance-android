package com.ransyadev.ssjattendance.network.response

import com.google.gson.Gson
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/** Error Code. */
object Error {
    const val REQUEST_FAILED = 400
    const val REQUEST_UNAUTHORIZED = 401
    const val REQUEST_NOT_FOUND = 404
    const val REQUEST_UNPROCESSABLE_ENTITY = 422
    const val REQUEST_TOO_MANY = 429
}

/** Error type when no internet connection. */
object NoInternetError : SSJResponse.Error(message = "No Internet")

/** Error type when connection is time out. */
object TimeOutError : SSJResponse.Error(message = "Time Out")

/** Error type when API response is not 2xx code. */
data class HttpError(
    override val message: String,
    val code: Int,
    val data: Any?,
) : SSJResponse.Error(message = message)

/** [data class/object] WhateverError : MoladinResponse.Error(message = ..., meta = ...) */
fun Exception.toError(): SSJResponse.Error {
    return try {
        when {
            this is IOException && message == "No Internet" -> {
                NoInternetError
            }
            this is HttpException -> {
                val error = Gson().fromJson(
                    response()?.errorBody()?.string().orEmpty(),
                    RawHttpError::class.java,
                )
                HttpError(
                    message = error.message ?: message(),
                    code = error.code ?: code(),
                    data = error.data,
                )
            }
            this is SocketTimeoutException -> {
                TimeOutError
            }
            this is UnknownHostException -> {
                NoInternetError
            }
            /**
             * Feel free to add other general error type here
             * ...
             */
            else -> {
                SSJResponse.Error(message = message.orEmpty())
            }
        }
    } catch (e: Exception) {
        SSJResponse.Error(message = e.message.orEmpty())
    }
}


