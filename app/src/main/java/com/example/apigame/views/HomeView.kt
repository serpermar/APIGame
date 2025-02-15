package com.example.apigame.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.apigame.components.CardGame
import com.example.apigame.components.Loader
import com.example.apigame.components.MainTopBar
import com.example.apigame.util.Constants.Companion.COLOR_1
import com.example.apigame.util.Constants.Companion.COLOR_4
import com.example.apigame.viewModel.GamesViewModel


@Composable
fun HomeView(viewModel: GamesViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            MainTopBar(title = "GAMES", onClickBackButton = { }) {
                navController.navigate("SearchGameView")
            }
        }
    ) {
        ContentHomeView(viewModel = viewModel, pad = it, navController)
    }
}

@Composable
fun ContentHomeView(viewModel: GamesViewModel, pad: PaddingValues, navController: NavController) {
    //val games by viewModel.games.collectAsState()
    val gamesPage = viewModel.gamesPage.collectAsLazyPagingItems()
    var search by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(pad),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier
                .background(Color(COLOR_1))
        ) {
            items(gamesPage.itemCount) { index ->
                val item = gamesPage[index]
                if (item != null) {
                    CardGame(item) {
                        navController.navigate("DetailView/${item.id}")
                    }
                    Text(
                        text = item.name,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(COLOR_4),
                        modifier = Modifier.padding(start = 10.dp)
                    )
                }
            }
            when (gamesPage.loadState.append) {
                is LoadState.NotLoading -> Unit
                LoadState.Loading -> {
                    item {
                        Column(
                            modifier = Modifier
                                .fillParentMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Loader()
                        }
                    }
                }
                is LoadState.Error -> {
                    item {
                        Text(text = "Load Error")
                    }
                }
            }
        }
    }
}
