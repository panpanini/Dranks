package com.github.panpanini.drink

import kotlinx.serialization.Serializable

@Serializable
data class DrinkResponse (
    val drinks : List<Drink>?
)