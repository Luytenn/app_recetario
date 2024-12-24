package com.example.recipefood.data.source.remote.dto.recipe_list

import com.example.recipefood.domain.model.RecipeList.RecipesResponse

data class RecipesResponseDto(
    val number: Int,
    val offset: Int,
    val results: List<RecipeDto>,
    val totalResults: Int
)

fun RecipesResponseDto.toRecipesResponse(): RecipesResponse {
    return RecipesResponse(
        offset = offset,
        results = results.map { item -> item.toRecipe() },
    )
}