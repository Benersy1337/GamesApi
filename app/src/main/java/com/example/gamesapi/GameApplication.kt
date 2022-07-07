package com.example.gamesapi

import android.app.Application
import com.example.gamesapi.data.local.GamesDatabase
import com.example.gamesapi.data.repository.GameRepository

class GameApplication : Application() {

    private val database: GamesDatabase by lazy {
        GamesDatabase.getInstance(this)
    }

    val repository: GameRepository by lazy {
        GameRepository(database.gameDao())
    }
}

