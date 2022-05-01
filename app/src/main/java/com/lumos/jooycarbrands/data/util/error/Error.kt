package com.lumos.jooycarbrands.data.util.error


import com.google.gson.annotations.SerializedName

data class Error(
    val errorCode: Any?,
    val localizedMessage: String?,
    val message: String?,
    val srcMessage: String?,
    val status: Int?,
    val statusCode: Int?,
    val translatedMessage: String?
)