package com.github.panpanini.cocktail

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(
    val name: String,
    val measure: String
)