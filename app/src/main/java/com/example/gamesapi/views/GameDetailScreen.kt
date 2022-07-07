package com.example.gamesapi.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.gamesapi.data.domain.local.Game
import com.example.gamesapi.R

@Composable
fun GameDetailScreen(
    game: Game
){
    Column(
        modifier = Modifier.background(Color.DarkGray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Text(
                text = game.title,
                style = MaterialTheme.typography.h3
            )
        }

        Row() {
            Column(
                modifier = Modifier
                    .weight(0.4f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(){
                    AsyncImage(
                        modifier = Modifier
                            .fillMaxWidth(),
                        alignment = Alignment.Center,
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(game.thumbnail)
                            .build(),
                        placeholder = painterResource(R.drawable.placeholder),
                        contentDescription = game.title,
                    )
                }
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ){
            Column(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(top = 15.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 80.dp, bottom = 5.dp),
                    text = "Informations",
                    style = MaterialTheme.typography.h4
                )
                Text(
                    modifier = Modifier
                        .padding(start = 7.dp, bottom = 4.dp),
                    text = "Game Id: "+game.id,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier
                        .padding(start = 7.dp, bottom = 4.dp),
                    text = "Name: "+game.title,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier
                        .padding(start = 7.dp, bottom = 4.dp),
                    text = "Developer: "+game.developer,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier
                        .padding(start = 7.dp, bottom = 4.dp),
                    text = "Genre: "+game.genre,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier
                        .padding(start = 7.dp, bottom = 4.dp),
                    text = "Realease Date: "+game.release_date,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    modifier = Modifier
                        .padding(start = 7.dp, bottom = 4.dp),
                    text = "Short Description: "+game.short_description,
                    fontWeight = FontWeight.Normal
                )

            }

        }
    }
}
