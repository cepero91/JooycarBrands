package com.lumos.jooycarbrands.ui.brand

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lumos.jooycarbrands.R
import com.lumos.jooycarbrands.ui.base.component.ErrorSnackBar
import com.lumos.jooycarbrands.ui.base.error.ErrorMessage
import com.lumos.jooycarbrands.ui.brand.component.ModelItem
import com.lumos.jooycarbrands.ui.brand.component.SubModelsList
import com.lumos.jooycarbrands.ui.brand.entities.BrandArgs
import com.lumos.jooycarbrands.ui.brand.viewmodel.ModelsViewModel
import kotlinx.coroutines.delay

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@ExperimentalFoundationApi
@Composable
fun ModelsBrandScreen(
    scaffoldState: ScaffoldState,
    navigateUp: () -> Unit,
    brandsSelected: BrandArgs?,
    modelsViewModel: ModelsViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val modelsUiState by modelsViewModel.modelUiState.collectAsState()
    val errorSnackBar by modelsViewModel.showErrorMessage.collectAsState(initial = null)

    LaunchedEffect(Unit) {
        modelsViewModel.initParameters(brandsSelected)
    }

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
            TopAppBar(
                title = {
                    Text(
                        text = "JOOYCAR MODELS",
                        style = MaterialTheme.typography.h4,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                            contentDescription = null
                        )
                    }
                }
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = it) { data ->
                ErrorSnackBar(message = data.message, onAction = {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                })
            }
        }
    ) {
        SwipeRefresh(
            state = rememberSwipeRefreshState(modelsUiState.isLoading),
            onRefresh = modelsViewModel::loadModels,
            modifier = Modifier.padding(it)
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                modelsUiState.models.entries.forEach { entry ->
                    stickyHeader {
                        Text(
                            text = entry.key,
                            style = MaterialTheme.typography.h3,
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colors.secondary)
                                .padding(8.dp),
                            color = Color.White
                        )
                    }
                    entry.value.forEach { model ->
                        item {
                            ModelItem(model = model)
                        }
                        item {
                            SubModelsList(subModels = model.subModels.orEmpty())
                        }
                    }
                }
            }
        }
    }
}