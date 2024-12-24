package com.example.recipefood.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.recipefood.ui.screen.details.RecipeDetails
import com.example.recipefood.ui.screen.details.RecipesDetailsScreen
import com.example.recipefood.ui.screen.details.RecipesDetailsScreenViewModel
import com.example.recipefood.ui.screen.favorites.FavoriteRecipesScreen
import com.example.recipefood.ui.screen.favorites.FavoritesViewModel
import com.example.recipefood.ui.screen.onboarding.WelcomeScreen
import com.example.recipefood.ui.screen.recipe.RecipesScreen
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Navigate.Screen.OnBoardingWelcome.route) {
            WelcomeScreen(navController = navController)
        }
        composable(route = Navigate.Screen.Main.route) {
            RecipesScreen(navController = navController)
        }
        composable(route = Navigate.Screen.RecipeDetails.route) {
            val recipeDetailVM = hiltViewModel<RecipesDetailsScreenViewModel>()
            RecipesDetailsScreen(navController = navController, onEvent = recipeDetailVM::onEvent)
        }
        composable(route = Navigate.Screen.FavoriteRecipe.route) {
            val favoriteRecipeVM = hiltViewModel<FavoritesViewModel>()
            FavoriteRecipesScreen(navController = navController)
        }

    }
}