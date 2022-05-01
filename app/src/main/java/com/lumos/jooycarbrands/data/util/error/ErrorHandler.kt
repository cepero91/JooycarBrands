package cl.difarma.ecommerce.data.util.error

import com.lumos.jooycarbrands.data.util.error.ErrorSource

interface ErrorHandler {
    fun getError(throwable: Throwable): ErrorSource
}
