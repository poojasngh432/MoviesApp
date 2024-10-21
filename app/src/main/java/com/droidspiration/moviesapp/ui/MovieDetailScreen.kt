package com.droidspiration.moviesapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.sharp.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(navController: NavHostController, viewModel: MoviesViewModel) {
    val movie = viewModel.selectedMovie.collectAsState().value
    val scrollState = rememberScrollState()

        Scaffold (
            topBar = {
                TopAppBar(
                    title = {  },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Sharp.ArrowBack,
                                contentDescription = "backIcon"
                            )
                        }
                    }
                )
            }
        ) {
                paddingValues ->
            if (movie == null) {
                // Show loading or error state when no movie is selected
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(paddingValues)
                        .verticalScroll(scrollState)
                        .padding(16.dp)
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
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(10.dp))
                            .size(400.dp),
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
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = movie.movieName,
                        color = Color.Black,
                        fontSize = 18.sp,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = movie.description, color = Color.Black)
                }
            }
        }
   }