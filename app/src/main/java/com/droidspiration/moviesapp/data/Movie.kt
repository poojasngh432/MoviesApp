package com.droidspiration.moviesapp.data

data class Movie (
    val id: Int,
    val movieName: String,
    val description: String = "",
    val imageUrl: String?
)