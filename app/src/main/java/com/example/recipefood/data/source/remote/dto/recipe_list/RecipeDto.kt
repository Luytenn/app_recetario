package com.example.recipefood.data.source.remote.dto.recipe_list

import com.example.recipefood.domain.model.RecipeList.Recipe

data class RecipeDto(
    val id: Int,
    val title: String,
    val image: String,
    val saved: Boolean
)

fun RecipeDto.toRecipe(): Recipe {
    return Recipe(
        id = id,
        title = title,
        image = image,
        saved = saved
    )
}