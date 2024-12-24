package com.example.recipefood.di

import android.app.Application
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.recipefood.data.repository.DataStoreRepository
import com.example.recipefood.data.repository.RecipeRepositoryImpl
import com.example.recipefood.data.source.local.RecipeDB
import com.example.recipefood.data.source.remote.Constants
import com.example.recipefood.data.source.remote.Constants.API_KEY
import com.example.recipefood.data.source.remote.FoodRecipesApi
import com.example.recipefood.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun getApi(okHttpClient: OkHttpClient): FoodRecipesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FoodRecipesApi::class.java)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor { message: String ->
            Log.d(TAG, message)
        }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val originalHttpUrl = chain.request().url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("apiKey", API_KEY).build()
                request.url(url)
                return@addInterceptor chain.proceed(request.build())
            }
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(30, TimeUnit.SECONDS)//30
            .writeTimeout(15, TimeUnit.SECONDS)//15
            .build()

    }

    @Provides
    @Singleton
    fun providerRecipeDatabaseLocal(app: Application): RecipeDB {
        return Room.databaseBuilder(app, RecipeDB::class.java,RecipeDB.DATABASE_NAME).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(
        api: FoodRecipesApi,
        db: RecipeDB,
    ): RecipeRepository {
        return RecipeRepositoryImpl(api,db.recipeDao)
    }

    @Provides
    @Singleton
    fun provideDataStoreRepository(
        @ApplicationContext context: Context
    ) = DataStoreRepository(context = context)

}