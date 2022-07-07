package com.example.gamesapi.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.gamesapi.data.domain.local.Game

@Entity
data class LocalGame(
    @PrimaryKey
    val id: Int,
    val developer: String,
    val game_url: String,
    val genre: String,
    val platform: String,
    val profile_url: String,
    val publisher: String,
    val release_date: String,
    val short_description: String,
    val thumbnail: String,
    val title: String
)

fun List<LocalGame>.asDomainModel(): List<Game> {
    return map {
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
