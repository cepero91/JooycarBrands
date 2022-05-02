package com.lumos.jooycarbrands.ui.brand.component

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.lumos.jooycarbrands.R

/**
 * Created by Cepero on 02/05/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@Composable
fun MultiSelectAppBar(
    multiSelectionEnable: Boolean,
    countChecked: Int,
    navigationIcon: @Composable () -> Unit,
    onDoneClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                text = if (multiSelectionEnable)
                    stringResource(id = R.string.contextual_mode).format(
                        countChecked
                    ) else stringResource(id = R.string.app_name).uppercase(),
                style = MaterialTheme.typography.h4,
                color = Color.White
            )
        },
        navigationIcon = if (multiSelectionEnable) navigationIcon else null,
        backgroundColor = if (multiSelectionEnable) Color.Gray else MaterialTheme.colors.primary,
        actions = {
            if (multiSelectionEnable)
                IconButton(onClick = onDoneClicked) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_round_check_24),
                        contentDescription = null,
                        tint = Color.White
                    )
                }

        }
    )
}
