package com.example.recipefood.data.source.local

import com.example.recipefood.data.source.remote.dto.recipe_details.RecipeDetailsDto
import com.example.recipefood.data.source.remote.dto.recipe_details.toIngredient
import com.example.recipefood.domain.model.RecipeDetails.RecipeDetails
import com.example.recipefood.domain.model.RecipeList.Recipe

fun Recipe.toRecipeEntity() = RecipeEntity(
    id = id,
    title = title,
    image = image,
    saved = saved
)

fun RecipeEntity.toRecipe() = Recipe(
    id = id,
    title = title,
    image = image
)

fun RecipeDetailsDto.toRecipeDetails(): RecipeDetails {
    return RecipeDetails(
        id = id,
        title = title,
        aggregateLikes = aggregateLikes,
        dishTypes = dishTypes,
        instructions = instructions,
        pricePerServing = pricePerServing,
        readyInMinutes = readyInMinutes,
        servings = servings,
        image = image ?: "",
        ingredients = extendedIngredients.map { ingredient -> ingredient.toIngredient() }
    )
}
