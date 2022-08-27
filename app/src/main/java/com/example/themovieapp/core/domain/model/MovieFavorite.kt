package com.example.themovieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieFavorite(
    val movie: Movie,
    val favorite: Favorite
) : Parcelable