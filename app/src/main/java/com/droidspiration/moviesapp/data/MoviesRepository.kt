package com.droidspiration.moviesapp.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository(private val context: Context): IMovieRepo {
//    private val api = RetrofitClient.api

//    suspend fun getTrendingMovies(): List<Movie> {
//        return api.getTrendingMovies(BuildConfig.TMDB_API_KEY)
//    }

    fun getMoviesFromApi(onResult: (List<Movie>?) -> Unit) {
        RetrofitInstance.apiService.getMovies().enqueue(object : Callback<List<Movie>> {
            override fun onResponse(call: Call<List<Movie>>, response: Response<List<Movie>>) {
                onResult(response.body())
            }

            override fun onFailure(call: Call<List<Movie>>, t: Throwable) {
                onResult(null)
            }
        })
    }

    override fun fetchMovies(): List<Movie> {
        // Load JSON from assets
        val jsonString = context.assets.open("movies.json").bufferedReader().use { it.readText() }

        // Parse JSON to list of Movie objects
        val gson = Gson()
        val movieListType = object : TypeToken<List<Movie>>() {}.type
        return gson.fromJson(jsonString, movieListType)
    }
}

interface IMovieRepo {
    fun fetchMovies(): List<Movie>
}