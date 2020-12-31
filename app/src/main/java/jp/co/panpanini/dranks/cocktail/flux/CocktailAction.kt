package jp.co.panpanini.dranks.cocktail.flux

import com.github.panpanini.cocktail.Cocktail


sealed class CocktailAction

data class UpdateCocktails(val cocktails: List<Cocktail>) : CocktailAction()

data class ShowLoading(val show: Boolean) : CocktailAction()

data class UpdateRecentSearches(val recentSearches: List<String>) : CocktailAction()

data class SetRecentSearchVisibility(val visible: Boolean) : CocktailAction()

object NoCocktailsFound : CocktailAction()