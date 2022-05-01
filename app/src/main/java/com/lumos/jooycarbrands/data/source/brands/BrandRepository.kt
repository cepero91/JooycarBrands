package com.lumos.jooycarbrands.data.source.brands

import com.lumos.jooycarbrands.data.source.brands.mapper.BrandDataMapper
import com.lumos.jooycarbrands.data.source.brands.mapper.ModelDataMapper
import com.lumos.jooycarbrands.data.source.brands.remote.BrandRemoteDataSource
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Brand
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Model
import com.lumos.jooycarbrands.util.Result
import com.lumos.jooycarbrands.util.map
import javax.inject.Inject

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
class BrandRepository @Inject constructor(
    private val brandRemoteDataSource: BrandRemoteDataSource,
    private val brandDataMapper: BrandDataMapper,
    private val modelDataMapper: ModelDataMapper
) : BrandDataSource {

    override suspend fun getBrands(): Result<List<Brand>> =
        brandRemoteDataSource.getBrands().map {
            map(brandDataMapper::fromDataToDomain)
        }

    override suspend fun getModels(brand: String): Result<List<Model>> =
        brandRemoteDataSource.getModels(brand).map {
            map(modelDataMapper::fromDataToDomain)
        }
}