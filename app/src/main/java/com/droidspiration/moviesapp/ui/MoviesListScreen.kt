package com.droidspiration.moviesapp.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.droidspiration.moviesapp.MoviesViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.bumptech.glide.request.RequestOptions
import com.droidspiration.moviesapp.R
import com.droidspiration.moviesapp.data.Movie
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesListScreen(navController: NavHostController, viewModel: MoviesViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }

    val movies by viewModel.movies.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMovies()
    }

    Column {
        SearchBar(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            shape = RoundedCornerShape(10.dp),
            query = searchQuery,
            onQueryChange = {
                searchQuery = it
            },
            onSearch = {
                active = false
                viewModel.searchMovies(searchQuery)
            },
            active = active,
            onActiveChange = {
                active = it
            },
            placeholder = {
                Text(text = "Search movies")
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search icon")
            }
        ) {
        }
        Spacer(modifier = Modifier.width(30.dp))

        val state = rememberLazyGridState()
        LazyVerticalGrid(
            modifier = Modifier,
            columns = GridCells.Fixed(2),
            state = state,
            contentPadding = PaddingValues(8.dp)
        ) {
            items(movies.size) { index ->
                val movie: Movie = movies[index]
                MovieItem(movie, navController, viewModel)
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, navController: NavHostController, viewModel: MoviesViewModel) {
    Column (
        Modifier
            .padding(horizontal = 8.dp)
            .clickable {
                viewModel.selectMovie(movie)
                navController.navigate("movie_detail")
            }
    ) {
        GlideImage(
            imageModel = { movie.imageUrl },
            requestBuilder = {
                Glide.with(navController.context)
                    .asBitmap()
                    .placeholder(R.drawable.img)
                    .apply(
                        RequestOptions()
                        .diskCacheStrategy(DiskCacheStrategy.ALL))
                    .thumbnail(0.6f)
                    .transition(withCrossFade())
            },
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(10.dp)).size(170.dp),
            imageOptions = ImageOptions(contentScale = ContentScale.FillBounds),
            previewPlaceholder = R.drawable.img,
            loading = {
                Box(modifier = Modifier.matchParentSize()) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            },
            failure = {
                Text(text = "image request failed.")
            })
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = movie.movieName,
            color = Color.Black,
            fontSize = 12.sp,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Medium
        )
    }
}