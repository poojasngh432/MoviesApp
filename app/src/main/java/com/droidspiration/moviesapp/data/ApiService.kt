package com.droidspiration.moviesapp.data

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/movies")  // Replace with the actual endpoint
    fun getMovies(): Call<List<Movie>>

    @GET
    suspend fun search(
        @Query("s") movieName: String = "en-US",
        @Query("api_key") apiKey: String = "API_KEY"
    ): MovieResponse
}