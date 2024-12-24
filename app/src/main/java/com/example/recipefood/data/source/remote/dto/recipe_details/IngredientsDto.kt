package com.example.recipefood.data.source.remote.dto.recipe_details

import com.example.recipefood.domain.model.RecipeDetails.Ingredient

data class IngredientDto(
    val id: Int,
    val image: String?,
    val name: String,
    val amount: Double,
    val unit: String,
    val originalName: String,
)

fun IngredientDto.toIngredient(): Ingredient {
    return Ingredient(
        image = image,
        name = name,
        amount = amount,
        unit = unit
    )
}