package com.github.panpanini.drink

import com.github.panpanini.cocktail.Cocktail
import com.github.panpanini.cocktail.Ingredient
import kotlinx.serialization.Serializable

@Serializable
data class Drink (
    val idDrink : Int,
    val strDrink : String,
    val strDrinkAlternate : String?,
    val strTags : String?,
    val strVideo : String?,
    val strCategory : String?,
    val strIBA : String?,
    val strAlcoholic : String?,
    val strGlass : String?,
    val strInstructions : String?,
    val strDrinkThumb : String?,
    val strIngredient1 : String?,
    val strIngredient2 : String?,
    val strIngredient3 : String?,
    val strIngredient4 : String?,
    val strIngredient5 : String?,
    val strIngredient6 : String?,
    val strIngredient7 : String?,
    val strIngredient8 : String?,
    val strIngredient9 : String?,
    val strIngredient10 : String?,
    val strIngredient11 : String?,
    val strIngredient12 : String?,
    val strIngredient13 : String?,
    val strIngredient14 : String?,
    val strIngredient15 : String?,
    val strMeasure1 : String?,
    val strMeasure2 : String?,
    val strMeasure3 : String?,
    val strMeasure4 : String?,
    val strMeasure5 : String?,
    val strMeasure6 : String?,
    val strMeasure7 : String?,
    val strMeasure8 : String?,
    val strMeasure9 : String?,
    val strMeasure10 : String?,
    val strMeasure11 : String?,
    val strMeasure12 : String?,
    val strMeasure13 : String?,
    val strMeasure14 : String?,
    val strMeasure15 : String?,
    val strCreativeCommonsConfirmed : String?,
    val dateModified : String?
) {
    fun toCocktail(): Cocktail {
        return Cocktail(
            id = idDrink,
            name = strDrink,
            alternativeName = strDrinkAlternate,
            thumbUrl = strDrinkThumb,
            videoUrl = strVideo,
            alcoholic = strAlcoholic.equals("alcoholic", ignoreCase = true),
            category = strCategory,
            tags = strTags,
            glass = strGlass,
            iba = strIBA,
            creativeCommons = strCreativeCommonsConfirmed.equals("yes", ignoreCase = true),
            dateModified = dateModified,
            instructions = strInstructions,
            ingredients = getIngredientsList()
        )
    }

    private fun getIngredientsList() = listOfNotNull(
        strIngredient1 asIngredient strMeasure1,
        strIngredient2 asIngredient strMeasure2,
        strIngredient3 asIngredient strMeasure3,
        strIngredient4 asIngredient strMeasure4,
        strIngredient5 asIngredient strMeasure5,
        strIngredient6 asIngredient strMeasure6,
        strIngredient7 asIngredient strMeasure7,
        strIngredient8 asIngredient strMeasure8,
        strIngredient9 asIngredient strMeasure9,
        strIngredient10 asIngredient strMeasure10,
        strIngredient11 asIngredient strMeasure11,
        strIngredient12 asIngredient strMeasure12,
        strIngredient13 asIngredient strMeasure13,
        strIngredient14 asIngredient strMeasure14,
        strIngredient15 asIngredient strMeasure15
    )

    private infix fun String?.asIngredient(measure: String?): Ingredient? {
        if (this == null || measure == null) return null
        return Ingredient(this, measure)
    }
}