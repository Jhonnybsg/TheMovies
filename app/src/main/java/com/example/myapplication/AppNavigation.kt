package com.example.myapplication

sealed class Screen(val route: String) {
    object MovieList: Screen("movie_list")
    object MovieDetails: Screen("movie_details/{movie_id}")
}

