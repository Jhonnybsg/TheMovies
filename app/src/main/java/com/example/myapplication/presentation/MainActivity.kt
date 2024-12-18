package com.example.myapplication.presentation

import android.os.Bundle
import android.os.Debug
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myapplication.navigation.Screens
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Screens.MovieList.route) {

                    composable(route = Screens.MovieList.route) {
                        MovieListUi(onClickMovieDetail = { movieId ->
                            val routeWithArg =
                                Screens.MovieDetails.route.replace("{movie_id}", movieId.toString())
                            navController.navigate(
                                route = routeWithArg
                            )
                        })
                    }

                    composable(
                        route = Screens.MovieDetails.route,
                        arguments = listOf(navArgument("movie_id") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val movieId = backStackEntry.arguments?.getString("movie_id")
                        movieId?.let { id -> MovieDetailUI(id) }
                    }
                }
            }
        }
    }
}

