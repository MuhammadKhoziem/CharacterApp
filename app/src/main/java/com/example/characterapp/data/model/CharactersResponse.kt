package com.example.characterapp.data.model


import com.google.gson.annotations.SerializedName

data class CharactersResponse(
    @SerializedName("info")
    val info: Info = Info(),
    @SerializedName("results")
    val characters: List<Character> = listOf()
)