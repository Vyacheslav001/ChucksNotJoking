package com.example.chucksnotjoking.fragments.joke

import com.example.chucksnotjoking.data.Joke

sealed class JokeLoadState

object Loading : JokeLoadState()
data class Success(val joke: Joke) : JokeLoadState()
object Error : JokeLoadState()
object Updating : JokeLoadState()