

package com.example.recipefood.util

import com.example.recipefood.data.source.local.RecipeEntity
import com.example.recipefood.domain.model.RecipeDetails.Ingredient
import com.example.recipefood.ui.screen.details.RecipeDetails
import com.github.javafaker.Faker

private val faker: Faker by lazy {
    Faker()
}

internal val RECIPE_ITEM_FAKE = RecipeEntity(
    id = faker.number().digit().toInt(),
    title = faker.lorem().sentence(),
    image = faker.internet().image(),
    saved = false

)

internal val RECIPE_ITEM_DETAIL = com.example.recipefood.domain.model.RecipeDetails.RecipeDetails(
    id = faker.number().digit().toInt(),
    title = faker.lorem().sentence(),
    image = faker.internet().image(),
    aggregateLikes = 4,
    ingredients = emptyList(),
    dishTypes = emptyList(),
    instructions = "",
    pricePerServing = 0.0,
    readyInMinutes = 0,
    servings = 0,

    )
