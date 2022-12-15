package com.example.chucksnotjoking.network

import com.example.chucksnotjoking.data.Category
import com.example.chucksnotjoking.data.Joke

object NetworkHelper {
    private val client = ChuckNorrisJokesClientBuilder.build()

    suspend fun requestCategoryListFromApi(
        onLoad: (List<Category>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            val categoryList = client.getCategoryList()
                .map { category ->
                    Category(category)
                }
            onLoad.invoke(categoryList)
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }

    suspend fun requestJokeFromApi(
        category: String,
        onLoad: (Joke) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        try {
            val jokeResponse = client.getJoke(category)
            val joke = Joke(jokeResponse.text, category)
            onLoad.invoke(joke)
        } catch (e: Exception) {
            onError.invoke(e)
        }
    }
}