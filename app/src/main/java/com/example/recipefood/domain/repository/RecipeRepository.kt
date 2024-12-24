package com.example.recipefood.domain.repository

import androidx.paging.PagingSource
import com.example.recipefood.data.source.local.RecipeEntity
import com.example.recipefood.data.source.remote.dto.recipe_details.RecipeDetailsDto
import com.example.recipefood.domain.model.RecipeList.Recipe
import com.example.recipefood.domain.model.RecipeList.RecipesResponse
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun fetchRecipes(offset: Int): RecipesResponse

    suspend fun fetchRecipesDetails(id: Int): RecipeDetailsDto

    /*
    * Room Methods
    * */

    suspend fun saveRecipe(recipe: Recipe)

    fun getRecipes(): Flow<List<RecipeEntity>>

    suspend fun getRecipeById(idRecipe: Int): RecipeEntity

    suspend fun deleteRecipeById(recipe: Recipe)
}