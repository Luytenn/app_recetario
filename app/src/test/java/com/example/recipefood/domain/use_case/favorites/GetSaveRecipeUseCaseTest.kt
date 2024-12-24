package com.example.recipefood.domain.use_case.favorites

import com.example.recipefood.domain.repository.RecipeRepository
import com.example.recipefood.domain.use_case.information.GetRecipeInformationUseCase
import com.example.recipefood.util.RECIPE_ITEM_DETAIL
import com.example.recipefood.util.RECIPE_ITEM_FAKE
import com.example.recipefood.util.ResultWrapper
import com.github.javafaker.Faker
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.coVerifyAll
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GetSaveRecipeUseCaseTest {


    @RelaxedMockK
    private lateinit var recipeRepository: RecipeRepository

    lateinit var getSaveRecipeUseCase: GetSaveRecipeUseCase

    lateinit var saveRecipeSuspendUseCase: SaveRecipeSuspendUseCase

    lateinit var getRecipeoImformationSuspendUseCase: GetRecipeInformationUseCase


    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getSaveRecipeUseCase = GetSaveRecipeUseCase(recipeRepository)
        saveRecipeSuspendUseCase = SaveRecipeSuspendUseCase(recipeRepository)
        getRecipeoImformationSuspendUseCase =  GetRecipeInformationUseCase(recipeRepository)
    }

    @Test
    fun `get_recipes_favorite_from_table_successfully`() = runBlocking  {

        val mockRecipes = listOf(RECIPE_ITEM_FAKE)

        // mock the repository's behavior to return mock recipes
        coEvery { recipeRepository.getRecipes() } returns flowOf(mockRecipes)

        // call the use case
        val result = getSaveRecipeUseCase.invoke()

        //  verify the result
        val recipes = result.toList() // Collect Flow data into a list
        assertEquals(mockRecipes, recipes.flatten()) // verify the recipes are returned correctly

        // verify that the repository method was called
        coVerify { recipeRepository.getRecipes() }
    }


    /*
    @Test
    fun `should emit loading and then success`() = runBlocking {
        // Arrange: Mock repository response to return RecipeDetails
        val myValue = RECIPE_ITEM_DETAIL
        coEvery { recipeRepository.fetchRecipesDetails(715415).toRecipeDetails() } returns myValue

        // Act: Execute the use case
        val result = getRecipeoImformationSuspendUseCase(715415)

        // Assert: Verify the flow emissions
        val firstEmission = result.first()
        assertTrue(firstEmission is ResultWrapper.Loading) // Should emit Loading first

        val secondEmission = result.first()
        assertTrue(secondEmission is ResultWrapper.Success)
        assertEquals(RECIPE_ITEM_DETAIL, (secondEmission as ResultWrapper.Success).data)

        // Verify that the repository method was called
        coVerify { recipeRepository.fetchRecipesDetails(715415) }
    }
    */


}