package com.lumos.jooycarbrands.domain.usecases.brands

import com.lumos.jooycarbrands.data.source.brands.BrandDataSource
import com.lumos.jooycarbrands.di.IoDispatcher
import com.lumos.jooycarbrands.domain.base.SuspendMapperUseCase
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Brand
import com.lumos.jooycarbrands.domain.usecases.brands.mapper.BrandMapperImpl
import com.lumos.jooycarbrands.ui.brand.entities.BrandPresentation
import com.lumos.jooycarbrands.util.Result
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
class GetBrandsUseCase @Inject constructor(
    private val dataSource: BrandDataSource,
    @IoDispatcher dispatcher: CoroutineDispatcher,
    mapper: BrandMapperImpl
) : SuspendMapperUseCase<Unit, List<BrandPresentation>, List<Brand>>(dispatcher, mapper) {

    override suspend fun execute(parameters: Unit): Result<List<Brand>> = dataSource.getBrands()
}