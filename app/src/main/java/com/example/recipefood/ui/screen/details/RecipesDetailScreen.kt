@file:Suppress("UNUSED_EXPRESSION")

package com.example.recipefood.ui.screen.details

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.recipefood.domain.model.RecipeDetails.RecipeDetails
import com.example.recipefood.domain.model.RecipeList.Recipe
import com.example.recipefood.ui.components.ErrorView
import com.example.recipefood.ui.components.LoadingView
import com.example.recipefood.ui.screen.details.components.DetailsHeader
import com.example.recipefood.ui.screen.details.components.DetailsItems
import com.example.recipefood.ui.screen.details.components.DishTypes
import com.example.recipefood.ui.screen.details.components.Ingredients
import com.example.recipefood.ui.screen.details.components.Navbar
import com.example.recipefood.util.HttpStatusCode

@Composable
fun RecipesDetailsScreen(
    navController: NavController,
    viewModel: RecipesDetailsScreenViewModel = hiltViewModel(),
    onEvent: (RecipeDetailEvent) -> Unit
) {

    when (val recipeDetailsState = viewModel.uiState.collectAsStateWithLifecycle().value) {
        is RecipeDetailsUiState.Loading -> LoadingView()
        is RecipeDetailsUiState.Error -> ErrorView(message = HttpStatusCode.getMeaningfulMessage(
            recipeDetailsState.message, LocalContext.current
        ), onClickRetry = {
            onEvent(RecipeDetailEvent.LoadRecipeDetail)
        })
        is RecipeDetailsUiState.Loaded -> RecipeDetails(
            recipeDetails = recipeDetailsState.data,
            navController = navController,
            isRecipeFavSaved = viewModel.isRecipeFavSaved.value
        ){  onEvent(RecipeDetailEvent.saveRecipeFavLocal(
            Recipe(id = recipeDetailsState.data.id, title = recipeDetailsState.data.title, image = recipeDetailsState.data.image))) }
    }
}

@Composable
fun RecipeDetails(navController: NavController, recipeDetails: RecipeDetails, isRecipeFavSaved: Boolean, onClickFav: () -> Unit) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Navbar(navController, isRecipeFavSaved) {  onClickFav() }
        Column(
            modifier = Modifier
                .scrollable(rememberScrollState(), Orientation.Horizontal)
                .verticalScroll(rememberScrollState())
                .weight(weight = 1f, fill = false)
                .fillMaxSize()
        ) {
            DetailsHeader(recipeDetails)
            DetailsItems(recipeDetails)
            DishTypes(recipeDetails.dishTypes)
            Ingredients(recipeDetails.ingredients)
        }
    }
}