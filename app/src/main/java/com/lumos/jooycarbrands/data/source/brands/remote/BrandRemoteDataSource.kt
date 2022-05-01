package com.lumos.jooycarbrands.data.source.brands.remote

import com.lumos.jooycarbrands.data.source.brands.entities.BrandResponse
import com.lumos.jooycarbrands.data.source.brands.entities.ModelResponse
import com.lumos.jooycarbrands.util.Result

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
interface BrandRemoteDataSource {

    suspend fun getBrands() : Result<List<BrandResponse>>
    suspend fun getModels(brand: String) : Result<List<ModelResponse>>
}
