package com.lumos.jooycarbrands.data.source.brands.remote

import com.lumos.jooycarbrands.data.source.brands.entities.BrandResponse
import com.lumos.jooycarbrands.data.source.brands.entities.ModelResponse
import com.lumos.jooycarbrands.data.source.brands.service.BrandService
import com.lumos.jooycarbrands.data.util.BaseRemoteDataSource
import com.lumos.jooycarbrands.util.Result
import javax.inject.Inject

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
class BrandRemoteDataSourceImpl @Inject constructor(
    private val service: BrandService
) : BrandRemoteDataSource, BaseRemoteDataSource() {

    override suspend fun getBrands(): Result<List<BrandResponse>> =
        getResult { service.getBrands() }

    override suspend fun getModels(brand: String): Result<List<ModelResponse>> =
        getResult { service.getModels(brand) }
}
