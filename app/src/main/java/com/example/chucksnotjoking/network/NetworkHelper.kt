package com.example.chucksnotjoking.network

import com.example.chucksnotjoking.data.Category

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
}