package com.lumos.jooycarbrands.ui.base.viewmodel

import androidx.lifecycle.ViewModel
import com.lumos.jooycarbrands.domain.base.entities.Failure
import com.lumos.jooycarbrands.extensions.launch
import com.lumos.jooycarbrands.ui.base.Event
import com.lumos.jooycarbrands.ui.base.error.ErrorMessage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
abstract class BaseViewModel : ViewModel() {

    val showErrorMessage get() = _showErrorMessage.receiveAsFlow()
    private val _showErrorMessage: Channel<Event<ErrorMessage?>> = Channel()

    fun onError(exception: Exception) {
        launch {
            _showErrorMessage.send(
                Event(
                    when (exception) {
                        is Failure.Source -> ErrorMessage.CustomMessage(message = exception.errorMessage.orEmpty())
                        is Failure.NoInternet -> ErrorMessage.NoInternetMessage
                        else -> ErrorMessage.GenericMessage
                    }
                )
            )
        }
    }
}