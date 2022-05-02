package com.lumos.jooycarbrands.ui.brand.entities


data class ModelPresentation(
    val brand: String?,
    val id: String?,
    val name: String?,
    val subModels: List<SubModelPresentation>?
)
