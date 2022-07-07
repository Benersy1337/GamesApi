package com.example.gamesapi.views

import android.util.Log
import androidx.lifecycle.*
import com.example.gamesapi.data.domain.local.Game
import com.example.gamesapi.data.repository.GameRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.IllegalArgumentException

class GamesViewModel(private val repository: GameRepository) : ViewModel() {

    init {
        if (repository.games.value.isNullOrEmpty()) {
            refreshDataFromRepository()
        }
    }

    val _gameList = repository.games

    val gameList: LiveData<List<Game>>
        get() {
            return _gameList
        }

    private val _eventNetworkError = MutableLiveData<String>("")

    private fun refreshDataFromRepository() {
        viewModelScope.launch {
            try {
                repository.refreshGames()
                _eventNetworkError.value = ""
            } catch (networkError: IOException) {
                Log.d("Error", "${networkError.message}")
                _eventNetworkError.value = networkError.message
            }
        }
    }

    fun getGameById(id: Int): Game {
        _gameList.value?.forEach {
            if (it.id == id) {
                return it
            }
        }
        return Game(
            "",
            "",
            "",
            -1,
            "",
            "",
            "",
            "",
            "",
            "",
            "",
        )
    }
}

class GameVMFactory(private val repository: GameRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GamesViewModel::class.java))
            return GamesViewModel(repository) as T
        throw IllegalArgumentException("Unknown ViewModel Class")
    }
}
