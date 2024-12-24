package com.example.recipefood.domain.model.RecipeList

data class Recipe(
    val id: Int,
    val title: String,
    val image: String,
    val saved: Boolean = false

)