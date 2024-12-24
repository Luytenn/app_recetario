package com.example.recipefood.data.source.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.recipefood.domain.model.RecipeList.Recipe

@Entity(
    tableName = "recipe_entity"
)
data class RecipeEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String,
    val saved: Boolean
)

