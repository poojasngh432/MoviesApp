package com.droidspiration.moviesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.droidspiration.moviesapp.data.Movie
import com.droidspiration.moviesapp.data.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(application: Application, private val repository: MoviesRepository = MoviesRepository(application)) : AndroidViewModel(application) {

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    fun fetchMovies() {
        viewModelScope.launch {
            _movies.value = repository.fetchMovies()
        }
    }

    fun selectMovie(movie: Movie) {
        _selectedMovie.value = movie
    }

    fun searchMovies(query: String) {
        viewModelScope.launch {
            val movies = repository.fetchMovies()

            _movies.value = movies.filter { it.description.contains(query, ignoreCase = true) }
        }
    }
}