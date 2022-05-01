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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.lumos.jooycarbrands.ui.brand.entities.ModelPresentation
import com.lumos.jooycarbrands.ui.brand.entities.SubModelPresentation

/**
 * Created by Cepero on 01/05/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@Composable
fun ModelItem(model: ModelPresentation) {
    Column(modifier = Modifier.padding(horizontal = 24.dp)) {
        Text(
            text = model.name.orEmpty(),
            style = MaterialTheme.typography.h4,
            modifier = Modifier.padding(top = 8.dp)
        )
        Divider(Modifier.padding(vertical = 8.dp))
        Text(
            text = "SUBMODELS:",
            style = MaterialTheme.typography.body1
        )
    }
}

@Composable
fun SubModelsList(subModels: List<SubModelPresentation>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = spacedBy(8.dp),
        contentPadding = PaddingValues(start = 24.dp, end = 24.dp, bottom = 24.dp, top = 8.dp)
    ) {
        items(subModels) { item ->
            SubModelItem(subModel = item)
        }
    }
}

@Composable
private fun SubModelItem(subModel: SubModelPresentation) {
    Card(elevation = 5.dp, shape = RoundedCornerShape(8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Text(text = "YEAR: ", style = MaterialTheme.typography.body1)
                Text(
                    text = subModel.year.toString(),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
            }
            Row {
                Text(text = "FUEL: ", style = MaterialTheme.typography.body1)
                Text(
                    text = subModel.fuelType.orEmpty(),
                    style = MaterialTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}