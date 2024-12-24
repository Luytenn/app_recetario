package com.example.recipefood.domain.model.RecipeDetails

data class Ingredient(
    val image: String?,
    val name: String,
    val amount: Double,
    val unit: String,
)