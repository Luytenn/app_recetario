package com.example.recipefood.data.repository

import androidx.paging.PagingSource
import com.example.recipefood.data.source.local.RecipeDao
import com.example.recipefood.data.source.local.RecipeEntity
import com.example.recipefood.data.source.local.toRecipeEntity
import com.example.recipefood.data.source.remote.FoodRecipesApi
import com.example.recipefood.data.source.remote.dto.recipe_details.RecipeDetailsDto
import com.example.recipefood.data.source.remote.dto.recipe_list.toRecipesResponse
import com.example.recipefood.domain.model.RecipeList.Recipe
import com.example.recipefood.domain.model.RecipeList.RecipesResponse
import com.example.recipefood.domain.repository.RecipeRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: FoodRecipesApi,
    private val dao: RecipeDao
): RecipeRepository  {
    override suspend fun fetchRecipes(offset: Int): RecipesResponse {
        return api.getRandomRecipes(offset).toRecipesResponse()
    }

    override suspend fun fetchRecipesDetails(id: Int): RecipeDetailsDto {
        return api.getRecipeDetails(id)
    }

    override suspend fun saveRecipe(recipe: Recipe) {
        return dao.saveRecipe(recipe.toRecipeEntity())
    }

    override  fun getRecipes(): Flow<List<RecipeEntity>> {
        return dao.getRecipes()
    }

     override suspend fun getRecipeById(idRecipe: Int): RecipeEntity {
        return dao.getRecipeById(idRecipe)
    }

    override suspend fun deleteRecipeById(recipe: Recipe) {
        return dao.deleteFavRecipeById(recipe.toRecipeEntity())
    }
}