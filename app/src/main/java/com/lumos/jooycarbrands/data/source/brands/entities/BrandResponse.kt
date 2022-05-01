package com.lumos.jooycarbrands.data.source.brands.entities


data class BrandResponse(
    val country: List<String>?,
    val created: Long?,
    val id: String?,
    val img: String?,
    val modif: Long?,
    val name: String?,
    val tags: List<Any>?
)