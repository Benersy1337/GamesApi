package com.example.gamesapi.data.source

import com.example.gamesapi.data.domain.local.Game
import com.example.gamesapi.data.local.LocalGame

data class SourceGameContainer(
    val sourceGames: List<SourceGame>
)

data class SourceGame(
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

fun SourceGameContainer.asDomainModel(): List<Game> {
    return sourceGames.map {
        Game(
            id = it.id,
            developer = it.developer,
            game_url =  it.game_url,
            genre =  it.genre,
            platform =  it.platform,
            profile_url = it.profile_url,
            publisher = it.publisher,
            release_date = it.release_date,
            short_description = it.short_description,
            thumbnail = it.thumbnail,
            title = it.title,
        )
    }
}

fun SourceGameContainer.asLocalModel(): List<LocalGame> {
    return sourceGames.map {
        LocalGame(
            id = it.id,
            developer = it.developer,
            game_url =  it.game_url,
            genre =  it.genre,
            platform =  it.platform,
            profile_url = it.profile_url,
            publisher = it.publisher,
            release_date = it.release_date,
            short_description = it.short_description,
            thumbnail = it.thumbnail,
            title = it.title,
        )
    }
}