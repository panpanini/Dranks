package jp.co.panpanini.dranks.detail.flux

import com.github.panpanini.cocktail.Cocktail


sealed class DetailAction

data class SetCocktail(val cocktail: Cocktail) : DetailAction()