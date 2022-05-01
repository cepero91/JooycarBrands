package com.lumos.jooycarbrands.ui.brand.state

import com.lumos.jooycarbrands.ui.brand.entities.ModelPresentation

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
data class ModelUiState(
    val isLoading: Boolean = false,
    val models: HashMap<String, List<ModelPresentation>> = hashMapOf()
)