package com.droidspiration.moviesapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MoviesRepository(private val context: Context) {
//    private val api = RetrofitClient.api

//    suspend fun getTrendingMovies(): List<Movie> {
//        return api.getTrendingMovies(BuildConfig.TMDB_API_KEY)
//    }

    fun fetchMovies(): List<Movie> {
        // Load JSON from assets
        val jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }

        // Parse JSON to list of Movie objects
        val gson = Gson()
        val movieListType = object : TypeToken<List<Movie>>() {}.type
        return gson.fromJson(jsonString, movieListType)
    }
}