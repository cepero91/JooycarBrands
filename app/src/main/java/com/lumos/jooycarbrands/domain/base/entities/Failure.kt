package com.lumos.jooycarbrands.domain.base.entities

sealed class Failure : Exception() {
    object NoInternet : Failure()

    object Generic : Failure()

    data class Source(
        val errorCode: String? = null,
        val errorMessage: String? = null
    ) : Failure()
}
