package com.example.recipefood.data.source.remote.dto.recipe_details

import com.example.recipefood.domain.model.RecipeDetails.RecipeDetails

data class RecipeDetailsDto(
    val id: Int,
    val title: String,
    val image: String?,
    val aggregateLikes: Int,
    val extendedIngredients: List<IngredientDto>,
    val dishTypes: List<String>,
    val instructions: String?,
    val pricePerServing: Double,
    val readyInMinutes: Int,
    val servings: Int

)

