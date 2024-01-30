package com.example.characterapp.data.model


import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("created")
    val created: String = "",
    @SerializedName("episode")
    val episode: List<String> = listOf(),
    @SerializedName("gender")
    val gender: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("image")
    val image: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("species")
    val species: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = ""
)