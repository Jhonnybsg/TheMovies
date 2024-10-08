package com.example.myapplication.data.network

sealed class NetworkResponse<T> {
    data class Success<T>(val data: T): NetworkResponse<T>()
    data class Failure<T>(val errorMessage: String): NetworkResponse<T>()
}
