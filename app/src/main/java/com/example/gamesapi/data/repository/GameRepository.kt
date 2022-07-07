package com.example.gamesapi.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.gamesapi.data.domain.local.Game
import com.example.gamesapi.data.local.GameDao
import com.example.gamesapi.data.local.asDomainModel
import com.example.gamesapi.data.source.GamesApi
import com.example.gamesapi.data.source.SourceGameContainer
import com.example.gamesapi.data.source.asLocalModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GameRepository(private val gameDao: GameDao) {
    val games: LiveData<List<Game>> = Transformations.map(
        gameDao.getAllGames()
    ) {
        it.asDomainModel()
    }

    suspend fun refreshGames() {
        withContext(Dispatchers.IO) {
            val games = GamesApi.retrofitService.getGames()
            val gamesContainer = SourceGameContainer(games)
            gameDao.insertAllGames(gamesContainer.asLocalModel())
        }
    }
}
