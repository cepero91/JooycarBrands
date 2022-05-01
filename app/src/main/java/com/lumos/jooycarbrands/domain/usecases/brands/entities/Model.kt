package com.lumos.jooycarbrands.domain.usecases.brands.entities


data class Model(
    val brand: String?,
    val id: String?,
    val name: String?,
    val subModels: List<SubModel>?
)