package com.lumos.jooycarbrands.ui.base

import java.util.Random

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
data class Event<out T>(
    val type: T,
    val trigger: Int = Random().nextInt(), // util for jetpack compose alert event in Channel
)