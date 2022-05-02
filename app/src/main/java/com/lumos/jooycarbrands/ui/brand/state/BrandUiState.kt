package com.lumos.jooycarbrands.ui.brand.state

import android.util.SparseBooleanArray
import com.lumos.jooycarbrands.ui.brand.entities.BrandPresentation
import com.lumos.jooycarbrands.util.Constants.ZERO

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
data class BrandUiState(
    val isLoading: Boolean = false,
    val brands: List<BrandPresentation> = listOf(),
    val selectedBrands: SparseBooleanArray = SparseBooleanArray(),
    val stateRefreshTrigger: Int = ZERO
)
