package com.lumos.jooycarbrands.domain.usecases.brands

import com.lumos.jooycarbrands.data.source.brands.BrandDataSource
import com.lumos.jooycarbrands.di.IoDispatcher
import com.lumos.jooycarbrands.domain.base.SuspendMapperUseCase
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Model
import com.lumos.jooycarbrands.domain.usecases.brands.mapper.ModelMapperImpl
import com.lumos.jooycarbrands.ui.brand.entities.ModelPresentation
import com.lumos.jooycarbrands.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
class GetModelsUseCase @Inject constructor(
    private val dataSource: BrandDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    mapper: ModelMapperImpl
) : SuspendMapperUseCase<String, List<ModelPresentation>, List<Model>>(dispatcher, mapper) {

    override suspend fun execute(parameters: String): Result<List<Model>> =
        dataSource.getModels(parameters)
}