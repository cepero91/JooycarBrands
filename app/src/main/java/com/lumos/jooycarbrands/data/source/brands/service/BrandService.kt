package com.lumos.jooycarbrands.data.source.brands.service

import com.lumos.jooycarbrands.data.source.brands.entities.BrandResponse
import com.lumos.jooycarbrands.data.source.brands.entities.ModelResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
interface BrandService {

    @GET(BRANDS)
    suspend fun getBrands() : List<BrandResponse>

    @GET(MODELS)
    suspend fun getModels(@Query(BRAND) brand: String) : List<ModelResponse>

    companion object {
        const val BRANDS = "brands"
        const val BRAND = "brand"
        const val MODELS = "models"
    }
}