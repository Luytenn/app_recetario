package com.example.recipefood.domain.model.RecipeList

data class RecipesResponse(
    val offset: Int,
    val results: List<Recipe>,
)