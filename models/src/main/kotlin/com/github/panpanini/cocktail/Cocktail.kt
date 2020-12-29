package com.github.panpanini.cocktail

import kotlinx.serialization.Serializable

@Serializable
data class Cocktail(
    val id : Int,
    val name : String,
    val alternativeName : String?,
    val tags : String?,
    val videoUrl : String?,
    val category : String?,
    val iba : String?,
    val alcoholic : Boolean,
    val glass : String?,
    val instructions : String?,
    val thumbUrl : String?,
    val ingredients: List<Ingredient>,
    val creativeCommons: Boolean,
    val dateModified : String?
)