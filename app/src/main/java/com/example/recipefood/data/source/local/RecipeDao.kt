package com.example.recipefood.data.source.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe_entity")
    fun getRecipes():  Flow<List<RecipeEntity>>

    @Query("SELECT * FROM recipe_entity WHERE id=:recipeId")
    suspend fun getRecipeById(recipeId: Int?): RecipeEntity

    @Delete()
    suspend fun deleteFavRecipeById(recipe: RecipeEntity)
}
