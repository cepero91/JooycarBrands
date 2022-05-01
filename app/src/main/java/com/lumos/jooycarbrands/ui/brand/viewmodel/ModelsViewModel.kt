package com.lumos.jooycarbrands.ui.brand.viewmodel

import com.lumos.jooycarbrands.domain.usecases.brands.GetModelsUseCase
import com.lumos.jooycarbrands.extensions.launch
import com.lumos.jooycarbrands.ui.base.viewmodel.BaseViewModel
import com.lumos.jooycarbrands.ui.brand.entities.BrandArgs
import com.lumos.jooycarbrands.ui.brand.entities.ModelPresentation
import com.lumos.jooycarbrands.ui.brand.state.ModelUiState
import com.lumos.jooycarbrands.util.getData
import com.lumos.jooycarbrands.util.getError
import com.lumos.jooycarbrands.util.isError
import com.lumos.jooycarbrands.util.isSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@HiltViewModel
class ModelsViewModel @Inject constructor(
    private val getModelsUseCase: GetModelsUseCase
) : BaseViewModel() {

    val modelUiState get() = _modelUiState.asStateFlow()

    private val _modelUiState = MutableStateFlow(ModelUiState())
    private var brandsSelected: BrandArgs? = null

    fun initParameters(brandsSelected: BrandArgs?) {
        this.brandsSelected = brandsSelected
        /* execute useCase */
        loadModels()
    }

    fun loadModels() {
        brandsSelected?.let { brands ->
            _modelUiState.update { it.copy(isLoading = true) }
            launch {
                /* load all models details in parallel requests */
                val asyncRequest = brands.selectedBrands.map {
                    async { getModelsUseCase(it.id.orEmpty()) }
                }

                val result = asyncRequest.awaitAll()

                val hashmap = hashMapOf<String, List<ModelPresentation>>()
                result.forEachIndexed { index, item ->
                    val primaryCondition = item.isSuccess && item.getData().isNotEmpty()
                    if (primaryCondition && index < brands.selectedBrands.size)
                        hashmap[brands.selectedBrands[index].name.orEmpty()] = item.getData()
                }

                _modelUiState.update {
                    it.copy(
                        isLoading = false,
                        models = hashmap
                    )
                }

                /* in case some request failed, show first error */
                result.firstOrNull {
                    it.isError
                }?.let {
                    onError(it.getError())
                }
            }
        }
    }
}