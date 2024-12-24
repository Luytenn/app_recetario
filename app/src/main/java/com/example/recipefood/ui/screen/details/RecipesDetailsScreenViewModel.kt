package com.example.recipefood.ui.screen.details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipefood.data.repository.DataStoreRepository
import com.example.recipefood.data.source.remote.Constants
import com.example.recipefood.domain.model.RecipeList.Recipe
import com.example.recipefood.domain.use_case.GetRecipesUseCase
import com.example.recipefood.domain.use_case.favorites.DeleteRecipeSuspendUseCase
import com.example.recipefood.domain.use_case.favorites.GetRecipeByIdUseCase
import com.example.recipefood.domain.use_case.favorites.SaveRecipeSuspendUseCase
import com.example.recipefood.domain.use_case.information.GetRecipeInformationUseCase
import com.example.recipefood.util.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesDetailsScreenViewModel @Inject constructor(
    private val recipesUseCase: GetRecipesUseCase,
    private val recipeUseCaseInfo: GetRecipeInformationUseCase,
    private val saveRecipeUseCase: SaveRecipeSuspendUseCase,
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val deleteRecipeUseCase: DeleteRecipeSuspendUseCase,
    private val repository: DataStoreRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiState = MutableStateFlow<RecipeDetailsUiState>(RecipeDetailsUiState.Loading)
    val uiState: StateFlow<RecipeDetailsUiState> = _uiState

    private val _isRecipeFavSaved: MutableState<Boolean> = mutableStateOf(false)
    val isRecipeFavSaved: State<Boolean> = _isRecipeFavSaved

    private val idRecipeKey = mutableStateOf(0)

    init {
        getIdRecipeDataStore()
        loadRecipeDetail()
        setFavoriteIconStatus()
        savedStateHandle.get<Int>(Constants.PARAM_ID)?.let { id ->
        }
    }

    fun setFavoriteIconStatus() {
        viewModelScope.launch() {

            val idRecipe = idRecipeKey.value
            val recipe = getRecipeByIdUseCase(idRecipe)

            if(recipe != null) {
                _isRecipeFavSaved.value = true
            } else{
                _isRecipeFavSaved.value = false
            }

        }
    }

    fun onEvent(event: RecipeDetailEvent) {
        when(event) {
            RecipeDetailEvent.LoadRecipeDetail -> {
                loadRecipeDetail()
            }
            is RecipeDetailEvent.saveRecipeFavLocal -> {
                saveRecipeFavoriteLocal(event.recipe)
            }
            is RecipeDetailEvent.getRecipeById -> {
                setFavoriteIconStatus()
            }
        }
    }

    private fun saveRecipeFavoriteLocal(recipe: Recipe) {

        viewModelScope.launch {
            //if recipe exist then remove it and change the favorite icon to not selected
            //if recipe doesnt exist then add it and change the favorite icon to selected
            if (_isRecipeFavSaved.value) {
                 deleteRecipeUseCase(recipe)
                _isRecipeFavSaved.value = false
            } else {
                saveRecipeUseCase(recipe)
                _isRecipeFavSaved.value = true
            }
        }

    }

    private fun loadRecipeDetail() {

        viewModelScope.launch{

            val idRecipe = idRecipeKey.value

            recipeUseCaseInfo(idRecipe).onEach {send ->
                when(send){
                    is ResultWrapper.Loading -> {
                        _uiState.value = RecipeDetailsUiState.Loading
                    }
                    is ResultWrapper.GenericError -> {
                        _uiState.value = RecipeDetailsUiState.Error(send.message.toString())
                    }
                    is ResultWrapper.Success -> {
                        _uiState.value = RecipeDetailsUiState.Loaded(recipesUseCase.getRecipeDetails(idRecipe))
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        }

    }

    private fun getRecipeDetails(id: Int) {
        _uiState.value = RecipeDetailsUiState.Loading
        viewModelScope.launch {
            try {
                _uiState.value = RecipeDetailsUiState.Loaded(recipesUseCase.getRecipeDetails(id))
            } catch (exception: Exception) {
                _uiState.value = RecipeDetailsUiState.Error(exception.message.toString())
            }
        }
    }

    fun getIdRecipeDataStore() {
        viewModelScope.launch() {
            idRecipeKey.value = repository.readInt("idRecipeKey")!!
        }
    }


}