package com.example.themovieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Detail(
    val movieId: Int,
    var createAt: Date?,
    val isFavorite:Boolean,
    val isWatch:Boolean
) : Parcelable