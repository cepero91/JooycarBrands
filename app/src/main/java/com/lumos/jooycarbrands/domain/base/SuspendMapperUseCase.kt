package com.lumos.jooycarbrands.domain.base

import com.lumos.jooycarbrands.domain.mapper.Mapper
import com.lumos.jooycarbrands.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

/**
 * It can be used for use cases that need map a domain Entity
 * into Presentation Entity
 */
abstract class SuspendMapperUseCase<in Params, out Results, Type>(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val mapper: Mapper<Type, Results>
) {
    suspend operator fun invoke(parameters: Params): Result<Results> {
        return try {
            withContext(coroutineDispatcher) {
                mapper.domainToPresentation(execute(parameters))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    @Throws(RuntimeException::class)
    protected abstract suspend fun execute(parameters: Params): Result<Type>
}
