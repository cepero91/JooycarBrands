package com.lumos.jooycarbrands.ui.route

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
sealed class BrandsRoute(val path: String) {
    object Brands : BrandsRoute("brands")
    object Models : BrandsRoute("models/{$BRAND_ARGS}")

    companion object {
        const val BRAND_ARGS = "args"
    }
}
