package com.example.gamesapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gamesapi.ui.theme.GamesApiTheme
import com.example.gamesapi.views.GameDetailScreen
import com.example.gamesapi.views.GameListScreen
import com.example.gamesapi.views.GameVMFactory
import com.example.gamesapi.views.GamesViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel by viewModels<GamesViewModel> {
            GameVMFactory(
                (this.applicationContext as GameApplication).repository
            )
        }
        setContent {
            GamesApiTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Game(viewModel)
                }
            }
        }
    }
}

@Composable
fun Game(
    viewModel: GamesViewModel
) {
    val navController = rememberNavController()
    Scaffold() {
        NavHost(navController = navController, startDestination = "gameList") {
            composable(route = "gameList") {
                GameListScreen(
                    gamesViewModel = viewModel,
                    navController
                )
            }
            composable(
                route = "gameList/{id}",
                arguments = listOf(navArgument("id") {
                    defaultValue = -1
                    type = NavType.IntType
                })
            ) {
                val id = it.arguments?.getInt("id") ?: -1
                val game = viewModel.getGameById(id)
                GameDetailScreen(game)
            }
        }
    }
}
