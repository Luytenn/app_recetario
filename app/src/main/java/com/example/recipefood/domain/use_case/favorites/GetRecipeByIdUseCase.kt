package com.example.recipefood.domain.use_case.favorites

import com.example.recipefood.data.source.local.RecipeEntity
import com.example.recipefood.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRecipeByIdUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository,
) {
    suspend operator fun invoke(idRecipe: Int): RecipeEntity {
        return recipesRepository.getRecipeById(idRecipe)
    }
}