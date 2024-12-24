package com.example.recipefood.ui.screen.details

import com.example.recipefood.domain.model.RecipeList.Recipe

sealed interface RecipeDetailEvent {
    object LoadRecipeDetail: RecipeDetailEvent
    data class saveRecipeFavLocal(val recipe: Recipe): RecipeDetailEvent
    data class getRecipeById(val idRecipe: Int): RecipeDetailEvent
}