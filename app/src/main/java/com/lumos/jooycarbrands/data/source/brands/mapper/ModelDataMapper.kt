package com.lumos.jooycarbrands.data.source.brands.mapper

import com.lumos.jooycarbrands.data.source.brands.entities.ModelResponse
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Model

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
interface ModelDataMapper {

    fun fromDataToDomain(data: ModelResponse): Model
}
