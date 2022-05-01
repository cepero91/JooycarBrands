package com.lumos.jooycarbrands.data.extensions

import com.lumos.jooycarbrands.data.util.error.ErrorSource
import com.lumos.jooycarbrands.domain.base.entities.Failure

fun Exception.toDomain() = when (this) {
    is ErrorSource.Network -> Failure.NoInternet
    is ErrorSource.ServiceError -> Failure.Source(errorCode, errorMessage)
    else -> Failure.Generic
}
