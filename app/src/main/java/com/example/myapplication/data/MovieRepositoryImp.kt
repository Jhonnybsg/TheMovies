package com.example.myapplication.data

import com.example.myapplication.data.model.MovieResponse
import com.example.myapplication.data.model.Root
import com.example.myapplication.data.network.MovieAPI
import com.example.myapplication.data.network.NetworkResponse
import com.example.myapplication.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImp @Inject constructor(private val movieApi: MovieAPI) : MovieRepository {
    override suspend fun getMovie(movieID: Int): Flow<NetworkResponse<MovieResponse>> = flow {
        try {
            emit(NetworkResponse.Success(movieApi.getMovieDetails(movieID)))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure("Something went wrong"))
        }
    }

    override suspend fun getTopRatedMovies(): Flow<NetworkResponse<Root>> = flow {
        try {
            emit(NetworkResponse.Success(movieApi.getTopRatedMovies()))
        } catch (e: Exception) {
            emit(NetworkResponse.Failure("Something went wrong: \n ${e.message}"))
        }
    }
}