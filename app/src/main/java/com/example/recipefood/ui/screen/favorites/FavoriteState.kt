package com.example.recipefood.ui.screen.favorites

import com.example.recipefood.data.source.local.RecipeEntity

data class FavoriteState(
    val recipes: List<RecipeEntity> = emptyList()
)