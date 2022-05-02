package com.lumos.jooycarbrands.ui.brand.component

import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.lumos.jooycarbrands.R
import com.lumos.jooycarbrands.ui.brand.entities.ModelPresentation
import com.lumos.jooycarbrands.ui.brand.entities.SubModelPresentation
import com.lumos.jooycarbrands.ui.theme.LargePd
import com.lumos.jooycarbrands.ui.theme.MediumPd
import com.lumos.jooycarbrands.ui.theme.NormalElevation
import com.lumos.jooycarbrands.ui.theme.NormalPd

/**
 * Created by Cepero on 01/05/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@Composable
fun ModelItem(model: ModelPresentation) {
    Column(modifier = Modifier.padding(horizontal = LargePd)) {
        Text(
            text = model.name.orEmpty(),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = MediumPd)
        )
        Divider(Modifier.padding(vertical = MediumPd))
        Text(
            text = stringResource(R.string.label_submodels),
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun SubModelsList(subModels: List<SubModelPresentation>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(MediumPd),
        contentPadding = PaddingValues(
            start = LargePd,
            end = LargePd,
            bottom = LargePd,
            top = MediumPd
        )
    ) {
        items(subModels) { item ->
            SubModelItem(subModel = item)
        }
    }
}

@Composable
private fun SubModelItem(subModel: SubModelPresentation) {
    Card(elevation = NormalElevation, shape = RoundedCornerShape(MediumPd)) {
        Column(modifier = Modifier.padding(NormalPd)) {
            Row {
                Text(
                    text = stringResource(R.string.label_year),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = subModel.year.toString(),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
            }
            Row {
                Text(
                    text = stringResource(R.string.label_fuel),
                    style = MaterialTheme.typography.body1
                )
                Text(
                    text = subModel.fuelType.orEmpty(),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}
