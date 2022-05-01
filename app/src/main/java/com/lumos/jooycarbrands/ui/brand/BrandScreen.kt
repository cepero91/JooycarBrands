package com.lumos.jooycarbrands.ui.brand

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberModalBottomSheetState
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
import com.lumos.jooycarbrands.extensions.countChecked
import com.lumos.jooycarbrands.ui.base.component.ErrorSnackBar
import com.lumos.jooycarbrands.ui.base.error.ErrorMessage
import com.lumos.jooycarbrands.ui.brand.component.BrandItem
import com.lumos.jooycarbrands.ui.brand.component.WelcomeBottomSheetContent
import com.lumos.jooycarbrands.ui.brand.entities.BrandArgs
import com.lumos.jooycarbrands.ui.brand.viewmodel.BrandViewModel
import kotlinx.coroutines.delay

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@Composable
fun BrandScreen(
    scaffoldState: ScaffoldState,
    brandViewModel: BrandViewModel = hiltViewModel(),
    navigateToModels: (BrandArgs) -> Unit
) {
    val context = LocalContext.current
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val brandsUiState by brandViewModel.brandUiState.collectAsState()
    val showWelcomeAlert by brandViewModel.showWelcomeBottomSheet.collectAsState(null)
    val multiSelectionEnable by brandViewModel.multiSelectEnabled.collectAsState()
    val goToModelsScreen by brandViewModel.goToModelsScreen.collectAsState(null)
    val errorSnackBar by brandViewModel.showErrorMessage.collectAsState(initial = null)

    val navigationIcon: @Composable (() -> Unit) = {
        IconButton(onClick = brandViewModel::onMultiSelectDisable) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                contentDescription = null,
                tint = Color.White
            )
        }
    }

    BackHandler(multiSelectionEnable) {
        brandViewModel.onMultiSelectDisable()
    }

    LaunchedEffect(showWelcomeAlert) {
        showWelcomeAlert?.let {
            if (it.type) {
                delay(800L)
                modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
            } else modalBottomSheetState.animateTo(ModalBottomSheetValue.Hidden)
        }
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

    LaunchedEffect(goToModelsScreen) {
        goToModelsScreen?.let {
            navigateToModels(BrandArgs(it))
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            WelcomeBottomSheetContent(onHide = brandViewModel::onWelcomeBottomSheetHide)
        }
    ) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = if (multiSelectionEnable)
                                "%s SELECTED".format(
                                    brandsUiState.selectedBrands.countChecked()
                                ) else "JOOYCAR BRANDS",
                            style = MaterialTheme.typography.h4,
                            color = Color.White
                        )
                    },
                    navigationIcon = if (multiSelectionEnable) navigationIcon else null,
                    backgroundColor = if (multiSelectionEnable) Color.Gray else MaterialTheme.colors.primary,
                    actions = {
                        if (multiSelectionEnable)
                            IconButton(onClick = brandViewModel::onDoneClicked) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_round_check_24),
                                    contentDescription = null,
                                    tint = Color.White
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
                state = rememberSwipeRefreshState(brandsUiState.isLoading),
                onRefresh = brandViewModel::loadBrands,
                modifier = Modifier.padding(it)
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    itemsIndexed(brandsUiState.brands) { index, item ->
                        BrandItem(
                            brand = item,
                            onClick = {
                                brandViewModel.onBrandPressed(index)
                            },
                            onLongClick = {
                                brandViewModel.onBrandPressed(index)
                            },
                            isSelected = brandsUiState.selectedBrands[index],
                            isMultiSelectionEnabled = multiSelectionEnable
                        )
                        Divider()
                    }
                }
            }
        }
    }
}