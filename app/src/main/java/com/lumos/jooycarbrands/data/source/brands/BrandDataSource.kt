package com.lumos.jooycarbrands.data.source.brands

import com.lumos.jooycarbrands.domain.usecases.brands.entities.Brand
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Model
import com.lumos.jooycarbrands.util.Result

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
interface BrandDataSource {

    suspend fun getBrands() : Result<List<Brand>>
    suspend fun getModels(brand: String) : Result<List<Model>>
}