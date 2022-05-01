package com.lumos.jooycarbrands.ui.brand.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.lumos.jooycarbrands.R
import com.lumos.jooycarbrands.ui.theme.LargePd
import com.lumos.jooycarbrands.ui.theme.NormalPd
import com.lumos.jooycarbrands.ui.theme.SmallPd
import com.lumos.jooycarbrands.ui.theme.WelcomeIconSize

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@Composable
fun WelcomeBottomSheetContent(
    onHide: () -> Unit
) {
    Column(
        modifier = Modifier.padding(NormalPd),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_round_emoji_emotions_24),
            contentDescription = null,
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(SmallPd)
                .size(WelcomeIconSize)
        )
        Text(
            text = stringResource(R.string.welcome_message),
            style = MaterialTheme.typography.h3,
            color = Color.Black,
            modifier = Modifier
                .fillMaxWidth()
                .padding(SmallPd),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(R.string.welcome_subtitle),
            style = MaterialTheme.typography.body1,
            color = Color.Black,
            modifier = Modifier
                .padding(horizontal = LargePd)
                .fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Button(onClick = onHide, modifier = Modifier
            .padding(NormalPd)
            .fillMaxWidth()) {
            Text(
                text = stringResource(R.string.accept_button),
                style = MaterialTheme.typography.button
            )
        }
    }
}