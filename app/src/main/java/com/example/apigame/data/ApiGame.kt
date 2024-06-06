package com.example.apigame.data

import com.example.apigame.model.GamesModel
import com.example.apigame.model.SingleGameModel
import com.example.apigame.util.Constants.Companion.API_KEY
import com.example.apigame.util.Constants.Companion.ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiGame {

    @GET(ENDPOINT + API_KEY)
    suspend fun getGame(): Response<GamesModel>

    @GET(ENDPOINT + API_KEY)
    suspend fun getGamesPaging(@Query("page") page: Int, @Query("page_size") pageSize: Int): GamesModel

    @GET("$ENDPOINT/{id}$API_KEY")
    suspend fun getGameById(@Path(value = "id")id: Int): Response<SingleGameModel>
}