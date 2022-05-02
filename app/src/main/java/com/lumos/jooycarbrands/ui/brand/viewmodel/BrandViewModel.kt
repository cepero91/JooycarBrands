package com.lumos.jooycarbrands.ui.brand.viewmodel

import android.util.Log
import android.util.SparseBooleanArray
import androidx.core.util.forEach
import com.lumos.jooycarbrands.domain.usecases.brands.GetBrandsUseCase
import com.lumos.jooycarbrands.extensions.ifNotEmpty
import com.lumos.jooycarbrands.extensions.launch
import com.lumos.jooycarbrands.ui.base.Event
import com.lumos.jooycarbrands.ui.base.viewmodel.BaseViewModel
import com.lumos.jooycarbrands.ui.brand.entities.BrandPresentation
import com.lumos.jooycarbrands.ui.brand.state.BrandUiState
import com.lumos.jooycarbrands.util.Constants.ONE
import com.lumos.jooycarbrands.util.Constants.ZERO
import com.lumos.jooycarbrands.util.dataOrNull
import com.lumos.jooycarbrands.util.getError
import com.lumos.jooycarbrands.util.isError
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import kotlin.random.Random

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@HiltViewModel
class BrandViewModel @Inject constructor(
    private val getBrandsUseCase: GetBrandsUseCase
) : BaseViewModel() {

    val brandUiState get() = _brandsUiState.asStateFlow()
    val showWelcomeBottomSheet get() = _showWelcomeBottomSheet.receiveAsFlow()
    val multiSelectEnabled get() = _multiSelectEnabled.asStateFlow()
    val goToModelsScreen get() = _goToModelsScreen.receiveAsFlow()

    private val _brandsUiState = MutableStateFlow(BrandUiState())
    private val _showWelcomeBottomSheet: Channel<Event<Boolean>> = Channel()
    private val _multiSelectEnabled = MutableStateFlow(false)
    private val _goToModelsScreen: Channel<List<BrandPresentation>> = Channel()

    /*
     * this counter is a very primitive way to avoiding
     * show alert after refresh. It is recommended use SharedPreferences
     * or some local storage mechanism
     *  */
    private var messageCount = ZERO

    init {
        loadBrands()
    }

    fun loadBrands() {
        _brandsUiState.update { it.copy(isLoading = true) }
        launch {
            getBrandsUseCase(Unit).let { result ->

                result.dataOrNull().let { list ->
                    _brandsUiState.update {
                        it.copy(
                            isLoading = false,
                            brands = list.orEmpty(),
                            selectedBrands = SparseBooleanArray(list?.size ?: ZERO)
                        )
                    }

                    /* show welcome bottom sheet only once */
                    showWelcomeIfNeeded(list)

                }

                /* show error popup */
                if (result.isError)
                    onError(result.getError())
            }
        }
    }

    private fun showWelcomeIfNeeded(list: List<BrandPresentation>?) {
        if (messageCount == ZERO) {
            list.ifNotEmpty {
                messageCount += ONE
                _showWelcomeBottomSheet.trySend(Event(true))
            }
        }
    }

    fun onWelcomeBottomSheetHide() {
        launch {
            _showWelcomeBottomSheet.send(Event(false))
        }
    }

    fun onBrandPressed(index: Int) {
        val currentValue = _brandsUiState.value.selectedBrands[index]
        _brandsUiState.update {
            it.copy(selectedBrands = it.selectedBrands.apply {
                put(index, !currentValue)
            }, stateRefreshTrigger = Random.nextInt())
        }
        _multiSelectEnabled.value = true
    }

    fun onMultiSelectDisable() {
        _multiSelectEnabled.value = false
        _brandsUiState.update {
            it.copy(selectedBrands = SparseBooleanArray(it.brands.size))
        }
    }

    fun onDoneClicked() {
        launch {
            val brandArgs = mutableListOf<BrandPresentation>()
            _brandsUiState.value.selectedBrands.forEach { key, value ->
                if (value)
                    brandArgs.add(_brandsUiState.value.brands[key])
            }
            _goToModelsScreen.send(brandArgs)
        }
    }
}