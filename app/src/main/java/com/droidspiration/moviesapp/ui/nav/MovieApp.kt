package com.droidspiration.moviesapp.ui.nav

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.droidspiration.moviesapp.MoviesViewModel
import com.droidspiration.moviesapp.MoviesViewModelFactory
import com.droidspiration.moviesapp.ui.MovieDetailScreen
import com.droidspiration.moviesapp.ui.MoviesListScreen
import com.droidspiration.moviesapp.ui.theme.MovieAppTheme

@Composable
fun MovieApp() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val application = context.applicationContext as Application

    MovieAppTheme {
        val viewModel: MoviesViewModel = viewModel(factory = MoviesViewModelFactory(application))
        NavHost(navController = navController, startDestination = "movie_list") {
            composable("movie_list") { MoviesListScreen(navController, viewModel) }
            composable("movie_detail") { MovieDetailScreen(navController, viewModel) }
        }
    }
}