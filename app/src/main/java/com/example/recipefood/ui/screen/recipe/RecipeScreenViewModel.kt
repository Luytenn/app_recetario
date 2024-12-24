package com.example.recipefood.ui.screen.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.recipefood.data.repository.DataStoreRepository
import com.example.recipefood.domain.model.RecipeList.Recipe
import com.example.recipefood.domain.use_case.GetRecipesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesScreenViewModel @Inject constructor(
    private val recipesUseCase: GetRecipesUseCase,
    private val repository: DataStoreRepository
) : ViewModel() {
    val recipes: Flow<PagingData<Recipe>> = Pager(PagingConfig(pageSize = 20)) {
        recipesUseCase
    }.flow.cachedIn(viewModelScope)

    fun saveIdRecipeSelected(idRecipe: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveInt("idRecipeKey", idRecipe)
        }
    }
}