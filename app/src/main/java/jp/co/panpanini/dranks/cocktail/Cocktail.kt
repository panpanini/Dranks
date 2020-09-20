package jp.co.panpanini.dranks.cocktail

import java.io.Serializable


data class Cocktail(
    val id : Int,
    val name : String,
    val alternativeName : String? = null,
    val tags : String? = null,
    val videoUrl : String? = null,
    val category : String? = null,
    val iba : String? = null,
    val alcoholic : Boolean = true,
    val glass : String? = null,
    val instructions : String? = null,
    val thumbUrl : String? = null,
    val ingredients: List<Ingredient> = listOf(),
    val creativeCommons: Boolean = false,
    val dateModified : String? = null
) : Serializable

data class Ingredient(
    val name: String,
    val measure: String
) : Serializable