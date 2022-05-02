package com.lumos.jooycarbrands.ui.brand.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@Parcelize
data class BrandPresentation(
    val country: List<String>?,
    val id: String?,
    val img: String?,
    val name: String?
) : Parcelable
