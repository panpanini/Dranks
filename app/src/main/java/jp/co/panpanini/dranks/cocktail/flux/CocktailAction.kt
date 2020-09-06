package jp.co.panpanini.dranks.cocktail.flux

import jp.co.panpanini.dranks.cocktail.Cocktail

sealed class CocktailAction

data class UpdateCocktails(val cocktails: List<Cocktail>) : CocktailAction()

object NoCocktailsFound : CocktailAction()