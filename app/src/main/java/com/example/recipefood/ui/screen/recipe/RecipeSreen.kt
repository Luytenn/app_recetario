package com.example.recipefood.ui.screen.recipe

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.recipefood.ui.components.ErrorView
import com.example.recipefood.ui.components.Header
import com.example.recipefood.ui.components.LoadingItem
import com.example.recipefood.ui.components.LoadingView
import com.example.recipefood.ui.components.RecipeItem
import com.example.recipefood.ui.navigation.Navigate
import com.example.recipefood.ui.screen.details.RecipeDetailsUiState
import com.example.recipefood.util.HttpStatusCode
import com.example.recipefood.util.HttpStatusCode.getHttpCodeFromLoadError

@Composable
fun RecipesScreen(
    navController: NavController, viewModel: RecipesScreenViewModel = hiltViewModel()
) {
    val lazyRecipesItems = viewModel.recipes.collectAsLazyPagingItems()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)  // Set background without affecting padding
            .padding(20.dp)  // Padding applied after background
        ) {
        Header() { navController.navigate(Navigate.Screen.FavoriteRecipe.route) }
        LazyColumn {
            items(lazyRecipesItems.itemCount) { index ->
                lazyRecipesItems[index]?.let {
                    println("item " + it.title)
                    RecipeItem(it, navController) {
                        viewModel.saveIdRecipeSelected(it.id)
                        println("RecipesScreen clicked")
                        navController.navigate(Navigate.Screen.RecipeDetails.route)
                    }
                }
            }

            lazyRecipesItems.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingView() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        val e = lazyRecipesItems.loadState.refresh as LoadState.Error

                        item {
                            ErrorView(message = HttpStatusCode.getMeaningfulMessage(
                                getHttpCodeFromLoadError(e.error.message.toString()),
                                LocalContext.current
                            ), onClickRetry = { retry() })
                        }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Error -> {
                        val e = lazyRecipesItems.loadState.append as LoadState.Error
                        item {
                            ErrorView(message = HttpStatusCode.getMeaningfulMessage(
                                getHttpCodeFromLoadError(e.error.localizedMessage!!),
                                LocalContext.current
                            ), onClickRetry = { retry() })
                        }
                    }
                }
            }
        }
    }
}