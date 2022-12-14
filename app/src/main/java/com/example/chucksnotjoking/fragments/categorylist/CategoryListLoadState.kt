package com.example.chucksnotjoking.fragments.categorylist

import com.example.chucksnotjoking.data.Category

sealed class CategoryListLoadState

object Loading : CategoryListLoadState()
data class Success(val categoryList: List<Category>) : CategoryListLoadState()
object Error : CategoryListLoadState()
object Updating : CategoryListLoadState()
