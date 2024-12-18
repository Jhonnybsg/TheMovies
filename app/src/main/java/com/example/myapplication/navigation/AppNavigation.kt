package com.example.myapplication.navigation

sealed class Screens(val route: String) {
    data object MovieList: Screens("movie_list")
    data object MovieDetails: Screens("movie_details/{movie_id}")
}