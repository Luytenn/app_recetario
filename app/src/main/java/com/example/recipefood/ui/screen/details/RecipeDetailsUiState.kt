package com.example.recipefood.ui.screen.details

import com.example.recipefood.domain.model.RecipeDetails.Ingredient
import com.example.recipefood.domain.model.RecipeDetails.RecipeDetails

sealed class RecipeDetailsUiState {
    object Loading: RecipeDetailsUiState()
    class Loaded(val data: RecipeDetails) : RecipeDetailsUiState()
    class Error(val message: String) : RecipeDetailsUiState()

}
