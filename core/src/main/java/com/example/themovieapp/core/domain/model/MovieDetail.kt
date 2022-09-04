package com.example.themovieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieDetail(
    val movie: Movie,
    val detail: Detail
) : Parcelable