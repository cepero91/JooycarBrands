package com.lumos.jooycarbrands.ui.base.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Snackbar
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import com.lumos.jooycarbrands.ui.theme.NormalPd

@Composable
fun JooycarSnackBar(
    message: String,
    messageColor: Color = White,
    backgroundColor: Color = Gray,
    actionMessage: String? = null,
    onAction: (() -> Unit)? = null,
    actionColor: Color = White
) {
    Snackbar(
        modifier = Modifier.padding(NormalPd),
        backgroundColor = backgroundColor,
        action = onAction?.let {
            {
                Text(
                    text = actionMessage.orEmpty(),
                    modifier = Modifier
                        .clickable {
                            it()
                        }
                        .padding(horizontal = NormalPd),
                    style = MaterialTheme.typography.button,
                    color = actionColor
                )
            }
        }
    ) {
        Text(text = message, style = MaterialTheme.typography.body1, color = messageColor)
    }
}
