package com.lumos.jooycarbrands.domain.usecases.brands.mapper

import com.lumos.jooycarbrands.domain.mapper.Mapper
import com.lumos.jooycarbrands.domain.usecases.brands.entities.Model
import com.lumos.jooycarbrands.ui.brand.entities.ModelPresentation
import com.lumos.jooycarbrands.ui.brand.entities.SubModelPresentation
import javax.inject.Inject

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
class ModelMapperImpl @Inject constructor() : Mapper<List<Model>, List<ModelPresentation>>() {

    override fun map(info: List<Model>): List<ModelPresentation> = with(info) {
        map {
            ModelPresentation(it.brand, it.id, it.name, it.subModels?.map { subModel ->
                SubModelPresentation(
                    subModel.compatibility,
                    subModel.fuelType,
                    subModel.id,
                    subModel.modif,
                    subModel.year
                )
            })
        }
    }
}