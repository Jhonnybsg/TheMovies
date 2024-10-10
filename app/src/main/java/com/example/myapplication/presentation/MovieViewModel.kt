package com.example.myapplication.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.network.NetworkResponse
import com.example.myapplication.domain.repositories.MovieRepository
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _moviesState = MutableStateFlow<UiState>(UiState.Loading)
    val moviesState: StateFlow<UiState> = _moviesState.asStateFlow()

    fun findMovie(movieId: Int = 11) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getMovie(movieId).collect { result ->
                when (result) {
                    is NetworkResponse.Success -> {
                        Log.d("Movie Response", "Title: ${result.data.title}")
                    }

                    is NetworkResponse.Failure -> {
                        Log.d("Movie Response", result.errorMessage)
                    }
                }
            }
        }
    }

    fun fetchTopRatedMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getTopRatedMovies().collect { result ->
                when (result) {
                    is NetworkResponse.Success -> {
                        _moviesState.value = UiState.Success(result.data.results)
                        Log.d("Movie Response", Gson().toJson(result.data.results))
                    }

                    is NetworkResponse.Failure -> {
                        _moviesState.value = UiState.Failure(result.errorMessage)
                        Log.d("Movie Response", result.errorMessage)
                    }
                }
            }
        }
    }
}

@OptIn(DelicateCoroutinesApi::class)
fun doSomething() {
    GlobalScope.launch {

    }
}

sealed class UiState {
    data class Success(val data: List<Movie>) : UiState()
    data class Failure(val errorMessage: String) : UiState()
    data object Loading : UiState()
}