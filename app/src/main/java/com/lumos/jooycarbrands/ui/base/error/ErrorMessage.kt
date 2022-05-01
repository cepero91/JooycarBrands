package com.lumos.jooycarbrands.ui.base.error

import androidx.annotation.StringRes
import com.lumos.jooycarbrands.R

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
sealed class ErrorMessage(@StringRes open val srcMessage: Int?) {

    data class CustomMessage(val message: String) : ErrorMessage(null)
    object NoInternetMessage : ErrorMessage(R.string.no_internet_message)
    object GenericMessage : ErrorMessage(R.string.generic_message)
}
