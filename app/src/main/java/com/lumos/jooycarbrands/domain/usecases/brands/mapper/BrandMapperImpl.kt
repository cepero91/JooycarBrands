package com.lumos.jooycarbrands.domain.usecases.brands.mapper

import com.lumos.jooycarbrands.domain.mapper.Mapper
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Brand
import com.lumos.jooycarbrands.ui.brand.entities.BrandPresentation
import javax.inject.Inject

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
class BrandMapperImpl @Inject constructor() : Mapper<List<Brand>, List<BrandPresentation>>() {

    override fun map(info: List<Brand>): List<BrandPresentation> = with(info) {
        map {
            BrandPresentation(it.country, it.id, it.img, it.name)
        }
    }
}