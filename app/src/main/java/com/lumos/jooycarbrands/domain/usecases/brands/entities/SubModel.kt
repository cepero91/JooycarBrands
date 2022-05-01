package com.lumos.jooycarbrands.domain.usecases.brands.entities


import com.google.gson.annotations.SerializedName

data class SubModel(
    val compatibility: String?,
    val fuelType: String?,
    val id: String?,
    val modif: Long?,
    val year: Int?
)