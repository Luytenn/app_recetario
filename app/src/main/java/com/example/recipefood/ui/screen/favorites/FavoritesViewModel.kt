package com.example.recipefood.ui.screen.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefood.domain.model.RecipeList.Recipe
import com.example.recipefood.domain.use_case.favorites.DeleteRecipeSuspendUseCase
import com.example.recipefood.domain.use_case.favorites.GetSaveRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val recipeUseCase: GetSaveRecipeUseCase,
    private val deleteRecipeSuspendUseCase: DeleteRecipeSuspendUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(FavoriteState())
    val state = _state.asStateFlow()

    init {
        loadRecipeLocal()
    }

    private fun loadRecipeLocal() {

        viewModelScope.launch {
            recipeUseCase().collect { recipeLocal ->
                println("data collected: " + recipeLocal)
                _state.update {
                    it.copy(
                        recipes = recipeLocal
                    )
                }
            }
        }

    }

    fun deleteFavRecipeSelected(recipe:Recipe){
        viewModelScope.launch {
            deleteRecipeSuspendUseCase(recipe)
        }
    }

}