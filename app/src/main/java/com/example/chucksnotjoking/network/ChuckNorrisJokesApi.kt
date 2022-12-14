package com.example.chucksnotjoking.network

import com.example.chucksnotjoking.data.Joke
import com.example.chucksnotjoking.data.Category
import com.example.chucksnotjoking.data.CategoryListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ChuckNorrisJokesApi {
    @GET("jokes/categories")
    suspend fun getCategoryList(): CategoryListResponse

    @GET("jokes/random")
    suspend fun getJoke(@Query("category") category: String): Joke
}