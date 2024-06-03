package com.example.apigame.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.apigame.viewModel.GamesViewModel
import com.example.apigame.views.DetailView
import com.example.apigame.views.HomeView
import com.example.apigame.views.SearchGameView


@Composable
fun NavManager(viewModel: GamesViewModel){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeView(viewModel, navController)
        }
        composable("DetailView/{id}/?{name}", arguments = listOf(
            navArgument("id") { type = NavType.IntType },
            navArgument("name") { type = NavType.StringType },
        )) {
            val id = it.arguments?.getInt("id") ?: 0
            val name = it.arguments?.getString("name") ?: ""
            DetailView(viewModel, navController, id, name)
        }
        composable("SearchGameView") {
            SearchGameView(viewModel, navController)
        }
    }
}