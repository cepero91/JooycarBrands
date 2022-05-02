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
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.ScaffoldState
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
import com.lumos.jooycarbrands.ui.base.component.JooycarScaffold
import com.lumos.jooycarbrands.ui.base.error.ErrorMessage
import com.lumos.jooycarbrands.ui.brand.component.BrandItem
import com.lumos.jooycarbrands.ui.brand.component.MultiSelectAppBar
import com.lumos.jooycarbrands.ui.brand.component.WelcomeBottomSheetContent
import com.lumos.jooycarbrands.ui.brand.entities.BrandArgs
import com.lumos.jooycarbrands.ui.brand.viewmodel.BrandViewModel
import com.lumos.jooycarbrands.ui.theme.NormalPd
import com.lumos.jooycarbrands.util.Constants.WELCOME_ALERT_ML
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

    /* states */
    val modalBottomSheetState =
        rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val brandsUiState by brandViewModel.brandUiState.collectAsState()
    val showWelcomeAlert by brandViewModel.showWelcomeBottomSheet.collectAsState(null)
    val multiSelectionEnable by brandViewModel.multiSelectEnabled.collectAsState()
    val goToModelsScreen by brandViewModel.goToModelsScreen.collectAsState(null)

    /* back icon for contextual  mode */
    val navigationIcon: @Composable (() -> Unit) = {
        IconButton(onClick = brandViewModel::onMultiSelectDisable) {
            Icon(
                painter = painterResource(id = R.drawable.ic_round_arrow_back_24),
                contentDescription = null,
                tint = Color.White
            )
        }
    }

    /* on back pressed handler */
    BackHandler(multiSelectionEnable) {
        brandViewModel.onMultiSelectDisable()
    }

    /* side effects */
    LaunchedEffect(showWelcomeAlert) {
        showWelcomeAlert?.let {
            if (it.type) {
                delay(WELCOME_ALERT_ML)
                modalBottomSheetState.animateTo(ModalBottomSheetValue.Expanded)
            } else modalBottomSheetState.animateTo(ModalBottomSheetValue.Hidden)
        }
    }

    LaunchedEffect(goToModelsScreen) {
        goToModelsScreen?.let {
            navigateToModels(BrandArgs(it))
        }
    }

    ModalBottomSheetLayout(
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = NormalPd, topEnd = NormalPd),
        sheetContent = {
            WelcomeBottomSheetContent(onHide = brandViewModel::onWelcomeBottomSheetHide)
        }
    ) {
        JooycarScaffold(
            scaffoldState = scaffoldState,
            viewModel = brandViewModel,
            topAppBar = {
                MultiSelectAppBar(
                    multiSelectionEnable = multiSelectionEnable,
                    countChecked = brandsUiState.selectedBrands.countChecked(),
                    navigationIcon = navigationIcon
                ) {
                    brandViewModel.onDoneClicked()
                }
            }
        ) {
            SwipeRefresh(
                state = rememberSwipeRefreshState(brandsUiState.isLoading),
                onRefresh = brandViewModel::loadBrands
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