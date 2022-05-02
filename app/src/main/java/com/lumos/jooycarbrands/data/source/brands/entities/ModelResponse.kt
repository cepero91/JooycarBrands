package com.lumos.jooycarbrands.data.source.brands.entities


data class ModelResponse(
    val brand: String?,
    val id: String?,
    val name: String?,
    val subModels: List<SubModelResponse>?
)
