package com.example.recipefood.domain.use_case.information

import android.util.Log
import com.example.recipefood.data.source.local.toRecipeDetails
import com.example.recipefood.domain.model.RecipeDetails.RecipeDetails
import com.example.recipefood.domain.repository.RecipeRepository
import com.example.recipefood.util.ResultWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetRecipeInformationUseCase @Inject constructor(
    private val recipesRepository: RecipeRepository
)  {
    operator fun invoke(idRecipe: Int): Flow<ResultWrapper<RecipeDetails>> {
        return flow {
            try {
                emit(ResultWrapper.Loading())
                val envio= recipesRepository.fetchRecipesDetails(idRecipe).toRecipeDetails()
                Log.i("getRecipeDetail","$envio")
                emit(ResultWrapper.Success(envio))

            }catch (e:Exception){
                emit(ResultWrapper.GenericError("${e.message}"))
            }
        }
    }
}
