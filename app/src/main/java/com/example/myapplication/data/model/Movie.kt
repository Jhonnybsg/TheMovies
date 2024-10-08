package com.example.myapplication.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val adult: Boolean,
    val backdrop_path: String,
    val genre_ids: ArrayList<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)

data class Root(
    val page: Int,
    @SerializedName("results")
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)
