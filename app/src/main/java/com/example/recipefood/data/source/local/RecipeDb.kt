package com.example.recipefood.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        RecipeEntity::class
    ],
    version = 5
)
//@TypeConverters(DataConverter::class)
abstract class RecipeDB : RoomDatabase() {
    abstract val recipeDao: RecipeDao
    companion object {
        const val DATABASE_NAME = "recipefood_db"
    }
}