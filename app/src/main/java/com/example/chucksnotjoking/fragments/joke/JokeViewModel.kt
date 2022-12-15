package com.example.chucksnotjoking.fragments.joke

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.chucksnotjoking.network.NetworkHelper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class JokeViewModel : ViewModel() {
    val state = MutableLiveData<JokeLoadState>()
    private lateinit var category: String

    fun setCategory(category: String) {
        this.category = category
        requestJoke(category, Loading)
    }

    fun onRetryClick() {
        requestJoke(category, Loading)
    }

    fun onUpdate() {
        requestJoke(category, Updating)
    }

    private fun requestJoke(category: String, loadingState: JokeLoadState) {
        state.postValue(loadingState)
        CoroutineScope(Dispatchers.IO).launch {
            NetworkHelper.requestJokeFromApi(category,
                { joke ->
                    state.postValue(Success(joke))
                }, {
                    state.postValue(Error)
                })
        }
    }
}