package com.lumos.jooycarbrands.domain.mapper

import com.lumos.jooycarbrands.util.Result
import com.lumos.jooycarbrands.util.isSuccess

abstract class Mapper<In, Out> {

    fun domainToPresentation(info: Result<In>): Result<Out> = when {
        info.isSuccess -> Result.Success(map((info as Result.Success).data!!))
        else -> Result.Error((info as Result.Error).exception)
    }

    abstract fun map(info: In): Out
}
