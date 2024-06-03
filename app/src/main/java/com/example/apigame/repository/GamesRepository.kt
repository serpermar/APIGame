package com.example.apigame.repository

import com.example.apigame.data.ApiGame
import com.example.apigame.model.GameList
import com.example.apigame.model.GamesModel
import com.example.apigame.model.SingleGameModel
import kotlinx.coroutines.delay
import javax.inject.Inject


class GamesRepository @Inject constructor(private val apiGame: ApiGame) {

    suspend fun getGames(): List<GameList>? {
        val response = apiGame.getGame()
        if (response.isSuccessful) {
            return response.body()?.results
        }
        return null
    }
    suspend fun getGamesPaging(page: Int, pageSize: Int): GamesModel {
        delay(3000L)
        return apiGame.getGamesPaging(page, pageSize)
    }

    suspend fun getGameById(id: Int): SingleGameModel? {
        val response = apiGame.getGameById(id)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }    suspend fun getGameByName(name: String): SingleGameModel? {
        val response = apiGame.getGameByName(name)
        if (response.isSuccessful) {
            return response.body()
        }
        return null
    }
}