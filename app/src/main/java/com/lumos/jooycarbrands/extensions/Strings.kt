package com.lumos.jooycarbrands.extensions

import com.google.gson.Gson

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
inline fun <reified T> String?.fromJsonToData(): T? = this?.let {
    try {
        Gson().fromJson(this, T::class.java)
    } catch (e: Exception) {
        null
    }
}