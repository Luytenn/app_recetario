package com.example.recipefood.util

import androidx.annotation.DrawableRes
import com.example.recipefood.R

sealed class OnBoardingPage(
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {
    object First : OnBoardingPage(
        image = R.drawable.recipe_food_image_1,
        title = "Search for recipes",
        description = "Browse and fin any recipe you are looking for. Also you can search by the ingredient you like."
    )

    object Second : OnBoardingPage(
        image = R.drawable.recipe_food_image_2,
        title = "Add your week meal plan",
        description = "Plan for the whole week, add the ingredients, browse recipes and add it easily and fast."
    )

    object Third : OnBoardingPage(
        image = R.drawable.recipe_food_image3,
        title = "save your liked recipes",
        description = "Save all your favourite recipes and get it later locally."
    )
}