package com.example.themovieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Favorite(
    val movieId: Int,
    var createAt: Date?
) : Parcelable