package com.lumos.jooycarbrands.data.util

import cl.difarma.ecommerce.data.util.error.ErrorHandler
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.lumos.jooycarbrands.data.extensions.toDomain
import com.lumos.jooycarbrands.data.util.error.ErrorApiResponse
import com.lumos.jooycarbrands.data.util.error.ErrorSource
import com.lumos.jooycarbrands.util.Result
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRemoteDataSource : ErrorHandler {

    protected suspend fun <Out> getResult(
        call: suspend () -> Out
    ): Result<Out> = try {
        Result.Success(call())
    } catch (e: Exception) {
        Result.Error(exception = getError(e).toDomain())
    }

    override fun getError(throwable: Throwable): ErrorSource = when (throwable) {
        is IOException -> ErrorSource.Network
        is HttpException -> getErrorFromBody(throwable.response()?.errorBody(), throwable.code())
        else -> ErrorSource.Unknown
    }

    private fun getErrorFromBody(errorBody: ResponseBody?, code: Int): ErrorSource {
        return errorBody?.let {
            try {
                val errorApi = Gson().fromJson(it.string(), ErrorApiResponse::class.java)
                ErrorSource.ServiceError(
                    errorCode = code.toString(),
                    errorMessage = errorApi.error?.message ?: errorApi.error?.srcMessage,
                )
            } catch (jsonException: JsonSyntaxException) {
                ErrorSource.Unknown
            }
        } ?: ErrorSource.Unknown
    }
}
