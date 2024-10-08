package com.example.myapplication.data.model

class MovieResponse(
    val adult: Boolean,
    val backdrop_path: String,
    val belongs_to_collection: BelongsToCollection,
    val budget: Int,
    val genres: ArrayList<Genre>,
    val homepage: String,
    val id: Int,
    val imdb_id: String,
    val origin_country: ArrayList<String>,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Float,
    val poster_path: String,
    val production_companies: ArrayList<ProductionCompany>,
    val production_countries: ArrayList<ProductionCountry>,
    val release_date: String? = null,
    val revenue: Int,
    val runtime: Int,
    val spoken_languages: ArrayList<SpokenLanguage>,
    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,
    val vote_average: Float,
    val vote_count: Int
)

class BelongsToCollection (
    val id: Int,
    val name: String,
    val poster_path: String,
    val backdrop_path: String
)

class Genre (
    val id: Int,
    val name: String
)

class ProductionCompany (
    val id: Int,
    val logo_path: String,
    val name: String,
    val origin_country: String
)

class ProductionCountry (
    val iso_3166_1: String,
    val name: String
)


class SpokenLanguage (
    val english_name: String,
    val iso_639_1: String,
    val name: String
)