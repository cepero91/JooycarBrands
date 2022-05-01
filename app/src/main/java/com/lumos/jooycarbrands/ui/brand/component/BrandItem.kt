package com.lumos.jooycarbrands.ui.brand.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberImagePainter
import com.lumos.jooycarbrands.R
import com.lumos.jooycarbrands.ui.brand.entities.BrandPresentation

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@ExperimentalFoundationApi
@Composable
fun BrandItem(
    brand: BrandPresentation,
    onClick: () -> Unit,
    onLongClick: () -> Unit,
    isMultiSelectionEnabled: Boolean = false,
    isSelected: Boolean
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .combinedClickable(
                onClick = {
                    if (isMultiSelectionEnabled)
                        onLongClick()
                    else onClick()
                },
                onLongClick = onLongClick
            )
            .padding(16.dp),
    ) {
        val (image, name, selector) = createRefs()
        val guideline = createGuidelineFromEnd(0.2f)

        Image(
            painter = rememberImagePainter(data = brand.img.orEmpty(), builder = {
                error(R.drawable.img_placeholder)
                placeholder(R.drawable.img_placeholder)
            }),
            contentDescription = null,
            modifier = Modifier
                .size(38.dp)
                .constrainAs(image) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start, margin = 16.dp)
                }
        )

        Text(
            text = brand.name.orEmpty(),
            style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.constrainAs(name) {
                top.linkTo(image.top)
                bottom.linkTo(image.bottom)
                start.linkTo(image.end, margin = 16.dp)
                end.linkTo(guideline)
                width = Dimension.fillToConstraints
            }
        )

        if (isMultiSelectionEnabled) {
            Icon(
                painter = painterResource(
                    id = if (isSelected) R.drawable.ic_round_check_circle_24 else R.drawable.ic_round_radio_button_unchecked_24
                ),
                contentDescription = null,
                tint = Color.Black,
                modifier = Modifier.constrainAs(selector) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(guideline)
                    end.linkTo(parent.end)
                }
            )
        }
    }
}