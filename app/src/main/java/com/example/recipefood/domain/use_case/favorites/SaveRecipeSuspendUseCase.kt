package com.example.recipefood.domain.use_case.favorites

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.recipefood.data.source.local.RecipeEntity
import com.example.recipefood.domain.model.RecipeList.Recipe
import com.example.recipefood.domain.repository.RecipeRepository
import javax.inject.Inject

class SaveRecipeSuspendUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository
) {
    suspend operator fun invoke(recipe: Recipe){
        recipesRepository.saveRecipe(recipe)
    }
}
