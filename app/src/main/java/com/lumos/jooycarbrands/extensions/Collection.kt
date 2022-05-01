package com.lumos.jooycarbrands.extensions

import android.util.SparseBooleanArray
import androidx.core.util.forEach

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
fun <T> Collection<T>?.ifNotEmpty(execute: () -> Unit) {
    if (!isNullOrEmpty())
        execute()
}

fun SparseBooleanArray.countChecked(): Int {
    var count = 0
    forEach { _, value ->
        if (value)
            count++
    }
    return count
}