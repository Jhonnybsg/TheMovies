package com.example.myapplication.domain.repositories

import com.example.myapplication.data.model.MovieResponse
import com.example.myapplication.data.model.Root
import com.example.myapplication.data.network.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getMovie(movieID: Int): Flow<NetworkResponse<MovieResponse>>
    suspend fun getTopRatedMovies(): Flow<NetworkResponse<Root>>
}