package com.droidspiration.moviesapp

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.droidspiration.moviesapp.data.Movie
import com.droidspiration.moviesapp.data.MoviesRepository
import com.droidspiration.moviesapp.ui.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MoviesViewModel(application: Application, private val repository: MoviesRepository = MoviesRepository(application)) : ViewModel() {

    private val _moviesState = MutableStateFlow<UIState<List<Movie>>>(UIState.Loading)
    val moviesState: StateFlow<UIState<List<Movie>>> = _moviesState

    private val _selectedMovie = MutableStateFlow<Movie?>(null)
    val selectedMovie: StateFlow<Movie?> = _selectedMovie

    fun fetchMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesState.value = UIState.Loading
            try {
                val movies = repository.fetchMovies()
                _moviesState.value = UIState.Success(movies)
            } catch (e: Exception) {
                _moviesState.value = UIState.Error("Failed to fetch movies: ${e.localizedMessage}")
            }
        }
    }

    fun selectMovie(movie: Movie) {
        _selectedMovie.value = movie
    }

    fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _moviesState.value = UIState.Loading
            try {
                val movies = repository.fetchMovies()
                val filteredMovies = movies.filter { it.movieName.contains(query, ignoreCase = true) }
                _moviesState.value = UIState.Success(filteredMovies)
            } catch (e: Exception) {
                _moviesState.value = UIState.Error("Failed to fetch movies: ${e.localizedMessage}")
            }
        }
    }
}