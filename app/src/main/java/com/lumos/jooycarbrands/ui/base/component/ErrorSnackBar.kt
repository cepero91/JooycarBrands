package com.lumos.jooycarbrands.ui.base.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.stringResource
import com.lumos.jooycarbrands.R

@Composable
fun ErrorSnackBar(
    message: String,
    onAction: (() -> Unit)? = null
) {
    JooycarSnackBar(
        message = message,
        backgroundColor = Red,
        actionMessage = stringResource(R.string.ok),
        onAction = onAction
    )
}
