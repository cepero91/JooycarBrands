package com.lumos.jooycarbrands.ui.base.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.lumos.jooycarbrands.R
import com.lumos.jooycarbrands.ui.base.error.ErrorMessage
import com.lumos.jooycarbrands.ui.base.viewmodel.BaseViewModel

/**
 * Created by Cepero on 02/05/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@Composable
fun JooycarScaffold(
    scaffoldState: ScaffoldState,
    viewModel: BaseViewModel,
    topAppBar: @Composable () -> Unit,
    content: @Composable () -> Unit
) {

    /* local */
    val context = LocalContext.current

    /* states */
    val errorSnackBar by viewModel.showErrorMessage.collectAsState(initial = null)

    /* side effect */
    LaunchedEffect(errorSnackBar) {
        errorSnackBar?.let { event ->
            scaffoldState.snackbarHostState.showSnackbar(
                message = when (val error = event.type) {
                    is ErrorMessage.CustomMessage -> error.message
                    else -> context.getString(error?.srcMessage ?: R.string.generic_message)
                }
            )
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            topAppBar()
        },
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                ErrorSnackBar(message = data.message, onAction = {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                })
            }
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            content()
        }
    }
}