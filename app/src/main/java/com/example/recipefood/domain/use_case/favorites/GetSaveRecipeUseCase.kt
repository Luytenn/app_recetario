package com.example.recipefood.domain.use_case.favorites

import com.example.recipefood.data.source.local.RecipeEntity
import com.example.recipefood.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSaveRecipeUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository,
) {
     operator fun invoke(): Flow<List<RecipeEntity>> {
        return recipesRepository.getRecipes()
    }
}