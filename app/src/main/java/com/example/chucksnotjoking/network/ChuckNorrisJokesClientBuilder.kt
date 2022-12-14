package com.example.chucksnotjoking.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ChuckNorrisJokesClientBuilder {
    fun build(): ChuckNorrisJokesApi {
        val client = Retrofit.Builder()
            .baseUrl("https://api.chucknorris.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return client.create(ChuckNorrisJokesApi::class.java)
    }

}