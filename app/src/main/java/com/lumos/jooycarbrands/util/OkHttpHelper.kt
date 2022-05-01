package com.lumos.jooycarbrands.util

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpHelper {

    fun getOkHttpBuilder(loggingInterceptor: HttpLoggingInterceptor) =
        OkHttpClient.Builder().apply {
            addInterceptor(loggingInterceptor)
        }
}
