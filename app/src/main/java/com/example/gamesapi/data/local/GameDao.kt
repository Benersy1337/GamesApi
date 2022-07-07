package com.example.gamesapi.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GameDao {
    @Query("SELECT * FROM localgame order by title")
    fun getAllGames(): LiveData<List<LocalGame>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllGames(games: List<LocalGame>)
}

