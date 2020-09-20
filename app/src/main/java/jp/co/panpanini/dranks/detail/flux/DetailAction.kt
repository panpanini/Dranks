package jp.co.panpanini.dranks.detail.flux

import jp.co.panpanini.dranks.cocktail.Cocktail

sealed class DetailAction

data class SetCocktail(val cocktail: Cocktail) : DetailAction()