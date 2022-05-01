package com.lumos.jooycarbrands.data.source.brands.entities


import com.google.gson.annotations.SerializedName

data class SubModelResponse(
    val compatibility: String?,
    val fuelType: String?,
    val id: String?,
    val modif: Long?,
    val year: Int?
)