package com.example.recipefood.ui.navigation

sealed class Navigate(val route: String) {
    sealed class Screen {
        object OnBoardingWelcome : Navigate("onboarding_welcome_screen")
        object Main: Navigate("main_screen")
        object RecipeDetails: Navigate("recipe_details_screen")
        object FavoriteRecipe: Navigate("favorite_recipe_screen")
    }
}

