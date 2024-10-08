package com.example.myapplication.di

import com.example.myapplication.common.BASE_URL
import com.example.myapplication.data.MovieRepositoryImp
import com.example.myapplication.data.network.MovieAPI
import com.example.myapplication.domain.repositories.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModules {

    @Binds
    abstract fun bindMovieRepository(movieRepositoryImp: MovieRepositoryImp): MovieRepository
}

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    @Provides
    fun providesRetrofitService(
    ): MovieAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(MovieAPI::class.java)
    }
}