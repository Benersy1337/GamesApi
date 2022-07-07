package com.example.gamesapi.data.domain.local

data class Game(
    val developer: String,
    val game_url: String,
    val genre: String,
    val id: Int,
    val platform: String,
    val profile_url: String,
    val publisher: String,
    val release_date: String,
    val short_description: String,
    val thumbnail: String,
    val title: String
)
