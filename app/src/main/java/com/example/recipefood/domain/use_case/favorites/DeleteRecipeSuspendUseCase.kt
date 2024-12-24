package com.example.recipefood.domain.use_case.favorites

import com.example.recipefood.data.source.local.RecipeEntity
import com.example.recipefood.domain.model.RecipeList.Recipe
import com.example.recipefood.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteRecipeSuspendUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository,
) {
    suspend operator fun invoke(recipe: Recipe) {
        return recipesRepository.deleteRecipeById(recipe)
    }
}