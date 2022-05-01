package com.lumos.jooycarbrands.data.source.brands.mapper

import com.lumos.jooycarbrands.data.source.brands.entities.ModelResponse
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Model
import com.lumos.jooycarbrands.domain.usecases.brands.entities.SubModel
import javax.inject.Inject

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
class ModelDataMapperImpl @Inject constructor() : ModelDataMapper {

    override fun fromDataToDomain(data: ModelResponse): Model = with(data) {
        Model(brand, id, name, subModels = subModels?.map {
            SubModel(it.compatibility, it.fuelType, it.id, it.modif, it.year)
        })
    }
}