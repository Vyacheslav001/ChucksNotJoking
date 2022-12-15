package com.example.chucksnotjoking.data

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("value")
    val text: String
)
