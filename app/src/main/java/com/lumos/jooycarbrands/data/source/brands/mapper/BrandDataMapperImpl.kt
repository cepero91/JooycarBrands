package com.lumos.jooycarbrands.data.source.brands.mapper

import com.lumos.jooycarbrands.data.source.brands.entities.BrandResponse
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Brand
import javax.inject.Inject

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
class BrandDataMapperImpl @Inject constructor() : BrandDataMapper {

    override fun fromDataToDomain(data: BrandResponse): Brand = with(data) {
        Brand(country, id, img, name)
    }
}
