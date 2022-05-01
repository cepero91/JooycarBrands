package com.lumos.jooycarbrands.ui.brand.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Cepero on 01/05/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@Parcelize
data class BrandArgs(val selectedBrands: List<BrandPresentation>) : Parcelable
