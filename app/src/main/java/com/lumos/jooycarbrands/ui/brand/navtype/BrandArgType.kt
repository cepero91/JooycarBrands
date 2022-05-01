package com.lumos.jooycarbrands.ui.brand.navtype

import android.os.Bundle
import androidx.navigation.NavType
import com.google.gson.Gson
import com.lumos.jooycarbrands.ui.brand.entities.BrandArgs

/**
 * Created by Cepero on 01/05/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
class BrandArgType : NavType<BrandArgs>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): BrandArgs? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): BrandArgs {
        return Gson().fromJson(value, BrandArgs::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: BrandArgs) {
        bundle.putParcelable(key, value)
    }
}