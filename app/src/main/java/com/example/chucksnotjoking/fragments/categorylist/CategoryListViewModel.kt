package com.example.chucksnotjoking.fragments.categorylist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucksnotjoking.network.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryListViewModel : ViewModel() {
    val state = MutableLiveData<CategoryListLoadState>()

    init {
        requestCategoryList(Loading)
    }

    fun onRetryClick() {
        requestCategoryList(Loading)
    }

    fun onUpdate() {
        requestCategoryList(Updating)
    }

    private fun requestCategoryList(loadingState: CategoryListLoadState) {
        state.postValue(loadingState)
        CoroutineScope(Dispatchers.IO).launch {
            NetworkHelper.requestCategoryListFromApi({ categoryList ->
                state.postValue(Success(categoryList))
            }, {
                state.postValue(Error)
            }
            )
        }
    }
}