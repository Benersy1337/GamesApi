package com.example.gamesapi.views

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gamesapi.data.domain.local.Game
import com.example.gamesapi.R
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.font.FontFamily

@Composable
fun GameListScreen(
    gamesViewModel: GamesViewModel,
    navController: NavController
) {
    val gamesList by gamesViewModel.gameList.observeAsState(listOf())
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(Color.Black)
    ) {
        GameList(
            gamesList,
            navController
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GameList(
    gameList: List<Game>,
    navController: NavController
) {
    LazyVerticalGrid(
        modifier = Modifier.background(Color.LightGray),
        cells = GridCells.Fixed(1)
    ) {
        items(gameList) {
            GameEntry(it) {
                navController.navigate("gameList/${it.id}")
            }
        }
    }
}

@Composable
fun GameEntry(
    game: Game,
    gameDetail: () -> Unit
) {
    val density = LocalDensity.current.density
    val width = remember { mutableStateOf(0F) }
    val height = remember { mutableStateOf(0F) }
    Card(
        modifier = Modifier
            .padding(15.dp)
            .clickable { gameDetail() },
        elevation = 30.dp

    ) {
        Box() {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(game.thumbnail)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.placeholder),
                contentDescription = game.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .onGloballyPositioned {
                        width.value = it.size.width / density
                        height.value = it.size.height / density
                    }
            )
            Box(
                modifier = Modifier
                    .size(width.value.dp, height.value.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color.Black),
                            300F,
                            900F,
                        )
                    )
            )
            Text(
                text = game.title,
                modifier = Modifier
                    .clickable { gameDetail() }
                    .align(Alignment.BottomCenter),
                style = MaterialTheme.typography.h5.copy(
                    color = Color.White,
                    fontWeight = FontWeight.W900,
                    fontFamily = FontFamily.Monospace
                )
            )
        }
    }
}

